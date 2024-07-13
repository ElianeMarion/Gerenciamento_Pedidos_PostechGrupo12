package br.com.ordertech.order.dtos;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.OrderDto;
import br.com.ordertech.order.dto.OrderLineDto;
import br.com.ordertech.order.enums.StatusEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

    @Test
    void testInstance() {
        OrderDto dto = OrderHelper.buildOrder();
        assertThat(dto.getOrderId()).isEqualTo(1L);
        assertThat(dto.getStatus()).isEqualTo(StatusEnum.WAITING_DELIVERY);
        assertThat(dto.getDeliveryDate()).isNull();
        assertThat(dto.getCustomerId()).isNotNull().isInstanceOf(Long.class);
        assertThat(dto.getOrderLine()).isNotNull().asList().element(0).isInstanceOf(OrderLineDto.class);
    }

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidation() {
        OrderDto orderDto = OrderDto.builder()
                .orderId(1L)
                .customerId(2L)
                .purchaseDate(LocalDateTime.now())
                .status(StatusEnum.WAITING_DELIVERY)
                .deliveryAddressId(3L)
                .orderLine(List.of(OrderHelper.buildOrderLine()))
                .build();

        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);

        assertThat(violations).isEmpty();
    }

    @Test
    public void testValidationWithNullFields() {
        OrderDto orderDto = OrderHelper.buildOrder();
        orderDto.setOrderId(null);
        orderDto.setPurchaseDate(null);
        orderDto.setStatus(null);
        orderDto.setDeliveryAddressId(null);

        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);

        assertThat(violations).hasSize(4);
    }

    @Test
    public void testValidationWithEmptyOrderLine() {

        OrderDto orderDto = OrderDto.builder()
                .orderId(1L)
                .customerId(2L)
                .purchaseDate(LocalDateTime.now())
                .status(StatusEnum.WAITING_DELIVERY)
                .deliveryAddressId(3L)
                .orderLine(List.of())
                .build();

        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);

        assertThat(violations).hasSize(1);
    }
}
