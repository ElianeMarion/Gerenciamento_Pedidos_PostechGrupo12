package br.com.ordertech.order.service;


import br.com.ordertech.order.consumer.CustomerClient;
import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.dto.ProductDto;
import br.com.ordertech.order.dto.UpdateProductStock;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StautsOrderEnum;
import br.com.ordertech.order.exceptions.CustomerNotFoundException;
import br.com.ordertech.order.exceptions.OrderNotFoundException;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.model.OrderLine;
import br.com.ordertech.order.producer.StockPedidoProducer;
import br.com.ordertech.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final StockPedidoProducer stockPedidoProducer;
    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;

    private final OrderLineService orderLineService;

    public OrderService(StockPedidoProducer stockPedidoProducer,
                        OrderRepository orderRepository,
                        @Qualifier("br.com.ordertech.order.consumer.CustomerClient") CustomerClient customerClient, OrderLineService orderLineService){
        this.stockPedidoProducer = stockPedidoProducer;
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.orderLineService = orderLineService;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order){
        try{


            order.getOrderLine().forEach(orderLine-> {

                BigDecimal price = stockPedidoProducer.getPrice(orderLine.getProductId());
                orderLine.setPrice(price);
                orderLine.setPrice(price.multiply(BigDecimal.valueOf(orderLine.getQuantity())));
                var quantityProductInStock = stockPedidoProducer.getQuantityProductById(orderLine.getProductId());
                // Verificação de estoque
                  if (orderLine.getQuantity() > quantityProductInStock) {
                      throw new RuntimeException("Estoque insuficiente");
                 }
                ProductDto product = new ProductDto(orderLine.getProductId(), orderLine.getQuantity());
                stockPedidoProducer.reserveProduct(product);
                product.setProductID(orderLine.getProductId());
                product.setQuantityStock(orderLine.getQuantity());
                product.setPrice(price);


                // Reserva de produtos
                this.stockPedidoProducer.reserveProduct(product);
                orderLineService.createOrderLine(orderLine);

            });
            order.setPurchaseDate(LocalDateTime.now());
            order.setDeliveryDate(null);
            order.setStatus(StatusEnum.WAITING_SEPARATION);
            CustomerDto customer = getCustomerById(1);
            if(customer == null)
                throw new CustomerNotFoundException("Cliente não retornado");
            order.setCustomerId(customer.getCustomerID());
            order.setDeliveryAddressId(customer.getAddressId());
            order.setOriginAddressId(1);
            order.setTotalOrderValue(totalOrderValue(order));
            order.setStatusOrder(StautsOrderEnum.WAITING_PAYMENT);
            return orderRepository.save(order);
        }
        catch (Exception e){
            throw new RuntimeException("Estoque insuficiente");
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
    }
    public CustomerDto getCustomerById(Integer customerId) {
        return customerClient.getCustomerById(customerId);
    }

    public Order updateStatusByStatusName(Long id, StatusEnum status){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Pedido não encontrado"));

        order.setStatus(status);
        orderRepository.save(order);
        return order;
    }

    public Order updateStatus(Long id, Integer statusCode){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new  OrderNotFoundException("Pedido não encontrado"));
        StatusEnum status = StatusEnum.fromCode(statusCode);

        order.setStatus(status);
        if(status.equals(StatusEnum.DELIVERY_COMPLETED))
            order.setDeliveryDate(LocalDateTime.now());
        orderRepository.save(order);
        return order;
    }

    public void canceledOrderReturnStock(Order order){
        UpdateProductStock productStock = new UpdateProductStock();
        order.getOrderLine().forEach(i-> {
            productStock.setProductId(i.getProductId());
            productStock.setAdditionalStock(i.getQuantity());
            this.stockPedidoProducer.incrementStockProduct(productStock);
        });
    }

    public Order updateStatus(Long id, StautsOrderEnum statusOrder){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new  OrderNotFoundException("Pedido não encontrado"));
       if(statusOrder.equals(StautsOrderEnum.APPROVED)) {
           order.setStatus(StatusEnum.WAITING_DELIVERY);
           orderRepository.save(order);
       }else if(statusOrder.equals(StautsOrderEnum.CANCELED))
           canceledOrderReturnStock(order);
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


}
