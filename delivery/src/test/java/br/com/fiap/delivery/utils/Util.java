package br.com.fiap.delivery.utils;


import br.com.fiap.delivery.dto.*;
import br.com.fiap.delivery.dto.route.*;
import br.com.fiap.delivery.enums.CourierStatus;
import br.com.fiap.delivery.enums.Status;
import br.com.fiap.delivery.model.Courier;
import br.com.fiap.delivery.model.Delivery;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class Util {

    public static String getId() {
        return "66697ff088bfc839ef76b634";
    }

    public static class Objects {

        public static Delivery buildDelivery() {
            Delivery delivery = new Delivery(buildOrderDeliveryFull());
            delivery.setDeliveryID(Util.getId());
            return delivery;
        }
        public static OrderDelivery buildOrderDeliveryFull() {
            OrderDelivery orderDelivery = new OrderDelivery();
            orderDelivery.setOrder(buildOrder());
            orderDelivery.setSenderAddress(buildAddress());
            return orderDelivery;
        }

        public static OrderDeliveryResponse buildOrderDeliveryResponse() {
            OrderDeliveryResponse resp = new OrderDeliveryResponse(buildOrder(), buildAddress());
            resp.setDeliveryID("dfg2567892hjdk");
            return resp;
        }

        public static OrderDeliveryResponse buildOrderDeliveryResponseWithCourier() {
            OrderDeliveryResponse resp = new OrderDeliveryResponse(buildOrder(), buildAddress());
            resp.setDeliveryID("dfg2567892hjdk");
            resp.setCourier(buildCourier());
            return resp;
        }

        public static AddressDto buildAddress() {
            AddressDto dto = new AddressDto();
            dto.setAddressID(1L);
            dto.setStreet("Rua Leblon");
            dto.setNumber(10);
            dto.setComplement("A");
            dto.setCity("Embu das Artes");
            dto.setState("SP");
            dto.setZipCode("06826270");
            dto.setSubSector(2);
            return dto;
        }

        public static OrderDto buildOrder() {
            OrderDto dto = new OrderDto();
            dto.setOrderID(1L);
            dto.setStatus(Status.WAITING_DELIVERY.getDesc());
            dto.setDeliveryDate(LocalDateTime.of(2024,5,2,15,12,59));
            dto.setPurchaseDate(LocalDateTime.of(2024,5,2,10,12,59));
            dto.setCustomer(buildCustomer());
            dto.setOrderLines(List.of(buildOrderLine()));
            return dto;
        }

        public static CustomerDto buildCustomer() {
            CustomerDto dto = new CustomerDto();
            dto.setCustomerID(1L);
            dto.setName("Maria da Compra");
            dto.setCpf("95859119062");
            dto.setPhoneNumber(11999992233L);
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

        public static ProductDto buildProduct() {
            ProductDto dto = new ProductDto();
            dto.setProductID(1L);
            dto.setName("Motorola Edge 30 Neo Dual SIM 128 GB very peri 8 GB RAM");
            dto.setDescription("Duas câmeras traseiras de 64 Mpx/13 Mpx, memória interna de 128GB...");
            dto.setPrice(BigDecimal.valueOf(2450.0));
            dto.setQuantityStock(100);
            return dto;
        }

        public static CourierDto buildCourier() {
            CourierDto dto = new CourierDto();
            dto.setCourierID("6670ed2661c1ca485c37ba6e");
            dto.setCourierName("Zezão do Grau");
            dto.setStatus("WAITING");
            dto.setLastDelivery(LocalDateTime.of(2024,5,13,20,23,59));
            return dto;
        }

        public static Courier buildCourierSaved() {
            Courier dto = new Courier();
            dto.setCourierID("6670ed2661c1ca485c37ba6e");
            dto.setCourierName("Zezão do Grau");
            dto.setStatus(CourierStatus.FREE.getDesc());
            dto.setLastDelivery(LocalDateTime.of(2024,5,13,20,23,59));
            return dto;
        }

        public static RouteRequest buildRouteRequest() {
            return new RouteRequest("rua urca, 10 - jd são vicente, embu das artes - sp",
                    "rua teodoro sampaio, 835 - pinheiros, são paulo - sp", true, "drive", true, true);
        }

        public static RouteResponse buildRouteResponse() {
            Route route = new Route();
            route.setDuration("69s");
            route.setPolyline(new PolyLine("r~koClwt|Ge@NYu@Yw@tBs@JCpA_@fCu@@ABA"));
            route.setDistanceMeters(296);
            route.setWarnings(List.of("teste"));
            route.setLocalizedValues(new LocalizedValues(new LocalizedValue("0,3 km"),
                    new LocalizedValue("1 min"), new LocalizedValue("1 min")));
            RouteResponse routeResponse = new RouteResponse(List.of(route));
            return routeResponse;
        }
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

}
