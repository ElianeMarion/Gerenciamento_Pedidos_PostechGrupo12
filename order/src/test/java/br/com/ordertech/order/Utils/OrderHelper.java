package br.com.ordertech.order.Utils;

import br.com.ordertech.order.dto.*;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.model.OrderLine;
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

    public static Order createOrder() {
        Order order = new Order();
        order.setOrderId(1l);
        order.setStatus(StatusEnum.WAITING_DELIVERY);
        order.setCustomerId(1);
        order.setPurchaseDate(LocalDateTime.now().plusMinutes(1));
        order.setDeliveryDate(null);
        order.setDeliveryAddressId(1);
        order.setOriginAddressId(1);
        order.setOrderLine(OrderHelper.addItens());
        order.setTotalOrderValue(new BigDecimal(20));
        return order;
    }

    public static AddressDto createAddress(){

        AddressDto address = new AddressDto(1,"Avenida Paulista", 54, "",
                "São Paulo", "SP", "565656", 28394999);
        return address;
    }

    public static AddressDto buildAddress() {
        AddressDto dto = new AddressDto();
        dto.setAddressID(1);
        dto.setStreet("Rua Leblon");
        dto.setNumber(10);
        dto.setComplement("A");
        dto.setCity("Embu das Artes");
        dto.setState("SP");
        dto.setZipCode("06826270");
        dto.setSubSector(2);
        return dto;
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
        dto.setCustomerID(1);
        dto.setName("João da Silva");
        dto.setCpf("95859119062");
        dto.setPhoneNumber(11999992233l);
        var address = buildAddress().getAddressID();
        dto.setAddressId(address);
        return dto;
    }

    public static OrderLineDto buildOrderLine() {
        OrderLineDto dto = new OrderLineDto();
        dto.setOrderLineID(1L);
        dto.setProduct(buildProduct());
        dto.setQuantity(1);
        dto.setPrice(BigDecimal.valueOf(1000.99));
        return dto;
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

        OrderDto order = new OrderDto();
        order.setCustomerId(-1); // Definindo um ID de cliente inválido
        return order;
    }
}
