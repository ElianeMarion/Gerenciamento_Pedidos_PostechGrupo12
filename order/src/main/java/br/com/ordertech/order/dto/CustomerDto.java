package br.com.ordertech.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    @Schema(name = "customerID",
            type = "Integer",
            description = "id do cliente",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Integer customerID;

    @Schema(name = "name",
            type = "String",
            description = "nome do cliente",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotEmpty
    private String name;

    @Schema(name = "cpf",
            type = "String",
            description = "cpf do cliente",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotEmpty
    private String cpf;

    @Schema(name = "phoneNumber",
            type = "Long",
            description = "Telefone do cliente",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Long phoneNumber;

    private Integer addressId;

}
