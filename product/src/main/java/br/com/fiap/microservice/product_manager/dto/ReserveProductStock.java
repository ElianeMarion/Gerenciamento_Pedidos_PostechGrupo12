package br.com.fiap.microservice.product_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveProductStock {
    private Long productId;
    private int QuantityRequired;
}
