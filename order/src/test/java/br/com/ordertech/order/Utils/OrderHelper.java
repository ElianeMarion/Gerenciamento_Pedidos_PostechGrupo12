package br.com.ordertech.order.Utils;

import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.dto.OrderDto;
import br.com.ordertech.order.dto.OrderLineDto;
import br.com.ordertech.order.dto.ProductDto;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.models.Address;
import br.com.ordertech.order.models.Order;
import br.com.ordertech.order.models.OrderLine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public abstract class OrderHelper {

    //public static Order

    public static List<OrderLine> addItens(){
        List<OrderLine> orderLines = new ArrayList<>();
        var orderLine = new OrderLine(1L,1L,1);
        orderLine.setPrice(BigDecimal.valueOf(10));
        orderLines.add(orderLine);
        List<OrderLine> itens = List.of(orderLine);
        return itens;
    }

    public static List<OrderLineDto> addItensDto(){
        List<OrderLineDto> orderLines = new ArrayList<>();
        var orderLine = buildOrderLine();
        orderLine.setPrice(BigDecimal.valueOf(10));
        orderLines.add(orderLine);

        return orderLines;
    }

    public static Order createOrder() {
        Order order = new Order();
        order.setOrderId(1l);
        order.setStatus(StatusEnum.WAITING_DELIVERY);
        order.setCustomerId(OrderHelper.buildCustomer().getCustomerID());
        order.setPurchaseDate(LocalDateTime.now().plusMinutes(1));
        order.setDeliveryDate(null);
        order.setDeliveryAddressId(1l);
        order.setOriginAddressId(1l);
        order.setOrderLine(OrderHelper.addItens());
        order.setTotalOrderValue(new BigDecimal(20));
        return order;
    }

    public static Address createAddress(){

        var address = new Address(1l,"Avenida Paulista", 54, "",
                "São Paulo", "SP", "565656", 28394999);
        return address;
    }


    public static ProductDto buildProduct() {
        ProductDto dto = new ProductDto();
        dto.setProductID(1L);
        dto.setName("Samsung A72 Dual SIM 128 GB very peri 8 GB RAM");
        dto.setDescription("Cinco câmeras traseiras de 64 Mpx/13 Mpx, memória interna de 128GB...");
        dto.setPrice(BigDecimal.valueOf(2450.0));
        dto.setQuantityStock(100);
        return dto;
    }

    public static CustomerDto buildCustomer() {
        CustomerDto dto = new CustomerDto();
        dto.setCustomerID(1L);
        dto.setName("João da Silva");
        dto.setCpf("95859119062");
        dto.setPhoneNumber("11999992233");
        var address = createAddress();
        dto.setAddress(address);
        return dto;
    }

    public static OrderLineDto buildOrderLine() {
       return OrderLineDto.builder()
                .orderLineID(1L)
                .product(buildProduct())
                .quantity(1)
                .price(BigDecimal.valueOf(1000.99))
        .build();

    }

    public static OrderDto buildOrder(){
        return OrderDto.builder()
                .orderID(1L)
                .statusOrder(StatusOrderEnum.WAITING_PAYMENT)
                .status(StatusEnum.WAITING_DELIVERY)
                .customerID(OrderHelper.buildCustomer().getCustomerID())
                .deliveryDate(null)
                .deliveryAddressId(OrderHelper.buildCustomer().getAddress().getAddressId())
                .orderLines(OrderHelper.addItensDto())
                .totalOrderValue(new BigDecimal(20))
                .build();
    }

    public static OrderLine createOrderLine() {
        var produtct = buildProduct();
        OrderLine dto = new OrderLine(1L, produtct.getProductID(), 1);
        dto.setPrice(BigDecimal.valueOf(1000.99));
        return dto;
    }

    public static String serialize(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch(Exception e) {
            log.error(e);
            return "{}";
        }
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static OrderDto createInvalidOrder() {
        OrderDto order = buildOrder();
        order.setCustomerID(-1L); // Definindo um ID de cliente inválido
        return order;
    }
}
