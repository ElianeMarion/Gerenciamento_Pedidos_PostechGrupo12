package br.com.ordertech.order.dto;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)

@Jacksonized
public class OrderDto {

    @NotNull
    @Valid
    private Long orderID;

    private Long customerID;
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
    private List<OrderLineDto> orderLines;

    private BigDecimal totalOrderValue;
    private StatusOrderEnum statusOrder;
    private UUID paymentId;


}
