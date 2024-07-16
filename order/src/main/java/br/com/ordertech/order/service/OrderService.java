package br.com.ordertech.order.service;

import br.com.ordertech.order.api.producer.PaymentEventProducer;
import br.com.ordertech.order.dto.*;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.exceptions.CustomerNotFoundException;
import br.com.ordertech.order.exceptions.OrderNotFoundException;
import br.com.ordertech.order.infra.CustomerClient;
import br.com.ordertech.order.infra.OrderDelivery;
import br.com.ordertech.order.infra.PaymentClient;
import br.com.ordertech.order.infra.StockPedidoProducer;
import br.com.ordertech.order.models.Address;
import br.com.ordertech.order.models.Order;
import br.com.ordertech.order.models.OrderLine;
import br.com.ordertech.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    private final StockPedidoProducer stockPedidoProducer;
    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;
    private final OrderDelivery orderDelivery;

    public OrderService(StockPedidoProducer stockPedidoProducer, OrderRepository orderRepository,
                        CustomerClient customerClient, OrderLineService orderLineService, PaymentEventProducer producer, PaymentClient paymentClient, OrderDelivery orderDelivery
                        ) {
        this.stockPedidoProducer = stockPedidoProducer;
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.orderLineService = orderLineService;
        this.paymentClient = paymentClient;
        this.orderDelivery = orderDelivery;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
    }
    public CustomerDto getCustomerById(Long customerId) {
        return customerClient.getCustomerById(customerId);
    }

    public Order saveOrder(Order order){
        try{
            order.setPurchaseDate(LocalDateTime.now());
            order.setDeliveryDate(null);
            order.setStatus(StatusEnum.WAITING_SEPARATION);
            CustomerDto customer = customerClient.getCustomerById(order.getCustomerId());
            if(customer == null)
                throw new CustomerNotFoundException("Cliente não retornado");

            order.setDeliveryAddressId(customer.getAddress().getAddressId());
            order.setOriginAddressId(1L);
            order.setStatusOrder(StatusOrderEnum.WAITING_PAYMENT);

            var savedOrder = orderRepository.save(order);

            log.info("Order" + savedOrder.getOrderId());

            order.getOrderLine().forEach(orderLine-> {
                BigDecimal price = stockPedidoProducer.getPrice(orderLine.getProductId());
                orderLine.setPrice(price.multiply(BigDecimal.valueOf(orderLine.getQuantity())));
                orderLine.setOrder(savedOrder);

                ProductDto product = new ProductDto(orderLine.getProductId(), orderLine.getQuantity());
                product.setPrice(orderLine.getPrice());
                orderLine.setProductId(product.getProductID());

                this.stockPedidoProducer.reserveProduct(product);

                orderLineService.createOrderLine(orderLine);
            });
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setOrderId(order.getOrderId());
            paymentDto.setValue(totalOrderValue(order));
            order.setPaymentId(paymentDto.getPaymentId());
            paymentClient.savePayment(paymentDto);

            return order;

        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Estoque insuficiente");
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Order updateStatusByStatusName(Long id, StatusEnum status){
        Order order = orderRepository.findByIdWithOrderLines(id)
                .orElseThrow(()-> new OrderNotFoundException("Pedido não encontrado"));
        order.setStatusOrder(StatusOrderEnum.APPROVED);
        order.setStatus(status);
        orderRepository.save(order);


        CustomerDto customerDto = customerClient.getCustomerById(order.getCustomerId());
        Address address = customerDto.getAddress();

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderID(order.getOrderId());
        orderRequest.setOrderLines(getOrderLines(order));
        orderRequest.setCustomer(customerDto);
        orderRequest.setSendAddress(customerDto.getAddress());
        orderRequest.setStatus(order.getStatus());
        orderRequest.setDeliveryDate(order.getDeliveryDate());
        orderRequest.setPurchaseDate(order.getPurchaseDate());
        OrderDeliveryDto deliveryDto = new OrderDeliveryDto(orderRequest, address);
        orderDelivery.saveOrderDelivery(deliveryDto);
        return order;
    }

    public Order updateStatus(Long id, Integer statusCode){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new  OrderNotFoundException("Pedido não encontrado"));
        StatusEnum status = StatusEnum.fromCode(statusCode);
        order.setStatus(status);
        if(status.equals(StatusEnum.WAITING_DELIVERY)){
            order.setStatusOrder(StatusOrderEnum.APPROVED);
        }
        if(status.equals(StatusEnum.DELIVERY_COMPLETED))
            order.setDeliveryDate(LocalDateTime.now());
        orderRepository.save(order);
        return order;
    }

    /**
     * O método devolve o valorTotal do pedido.
     * @return double
     * @param order
     *
     * */
    public BigDecimal totalOrderValue(Order order){
        BigDecimal price = BigDecimal.ZERO;
        for(OrderLine orderLine: order.getOrderLine()){
            price = price.add(orderLine.getPrice());
        }
        return price;
    }


    public void canceledOrderReturnStock(Order order){
        UpdateProductStock productStock = new UpdateProductStock();
        order.getOrderLine().forEach(i-> {
            productStock.setProductId(i.getProductId());
            productStock.setAdditionalStock(i.getQuantity());
            this.stockPedidoProducer.incrementStockProduct(productStock);
        });
    }

    public void canceledOrderReturnStock(Long id){
        var order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Pedido não encontrado"));
        UpdateProductStock productStock = new UpdateProductStock();
        order.getOrderLine().forEach(i-> {
            productStock.setProductId(i.getProductId());
            productStock.setAdditionalStock(i.getQuantity());
            this.stockPedidoProducer.incrementStockProduct(productStock);
        });
    }

    public List<OrderLineDto> getOrderLines(Order order){
        List<OrderLineDto> orderLines = new ArrayList<>();
        OrderLineDto orderLineDto = new OrderLineDto();
        List<ProductDto> products = stockPedidoProducer.getAll();

        order.getOrderLine().forEach(orderLine-> {

            Optional<ProductDto> foundProduct = products.stream()
                    .filter(product -> product.getProductID().equals(orderLine.getProductId()))
                    .findFirst();
            if (foundProduct.isPresent()) {
                ProductDto productDto = foundProduct.get();
                orderLineDto.setOrderLineID(orderLine.getOrderLineId());
                orderLineDto.setQuantity(orderLine.getQuantity());
                orderLineDto.setProduct(productDto);
                orderLineDto.setPrice(orderLine.getPrice());
                orderLines.add(orderLineDto);
            }
        });
        return orderLines;
    }



}
