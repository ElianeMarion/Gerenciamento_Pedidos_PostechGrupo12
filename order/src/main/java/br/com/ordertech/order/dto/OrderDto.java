package br.com.ordertech.order.dto;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)

@Jacksonized
public class OrderDto {

    @NotNull
    @Valid
    private Long orderId;

    private Long customerId;
    @NotNull
    private LocalDateTime purchaseDate;

    private LocalDateTime deliveryDate;

    @NotNull
    private StatusEnum status;

    @NotNull
    @Valid
    private Long deliveryAddressId;

    private Long originAddressId;

    @NotEmpty
    @Valid
    private List<OrderLineDto> orderLine;

    private BigDecimal totalOrderValue;
    private StatusOrderEnum statusOrder;


}
