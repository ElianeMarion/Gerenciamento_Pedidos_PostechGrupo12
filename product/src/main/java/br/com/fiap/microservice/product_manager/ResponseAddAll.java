package br.com.fiap.microservice.product_manager;

import br.com.fiap.microservice.product_manager.dto.AddProduct;
import br.com.fiap.microservice.product_manager.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseAddAll {
    private List<Product> addedProducts;
    private List<AddProduct> noAddedProducts;
}
