package br.com.fiap.microservice.product_manager.controller;

import br.com.fiap.microservice.product_manager.ResponseAddAll;
import br.com.fiap.microservice.product_manager.ResponseResult;
import br.com.fiap.microservice.product_manager.dto.AddProduct;
import br.com.fiap.microservice.product_manager.dto.ReserveProductStock;
import br.com.fiap.microservice.product_manager.dto.UpdateProductPrice;
import br.com.fiap.microservice.product_manager.dto.UpdateProductStock;
import br.com.fiap.microservice.product_manager.model.Product;
import br.com.fiap.microservice.product_manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listAllProducts() {
        return productService.listAll();
    }

    @GetMapping("{id}/price")
    public ResponseEntity<BigDecimal> getPrice(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getPrice(id));
    }

    @PostMapping
    public ResponseEntity<ResponseResult<?>> addProduct(@RequestBody AddProduct product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @PostMapping("add/all")
    public ResponseEntity<ResponseAddAll> addAllProduct(@RequestBody List<AddProduct> list) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addAllProduct(list));
    }

    @PostMapping("reserve")
    public ResponseEntity<ResponseResult<Boolean>> reserveProduct(@RequestBody ReserveProductStock reserveRequired) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.reserveProduct(
            reserveRequired.getProductId(), reserveRequired.getQuantityRequired()
        ));
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(product));
    }

    @PatchMapping("update/price")
    public ResponseEntity<Product> updatePriceOnlyProduct(@RequestBody UpdateProductPrice data) {
        return ResponseEntity.status(HttpStatus.OK).body(
            productService.updatePriceProduct(data.getProductId(), data.getPrice())
        );
    }

    @PatchMapping("update/stock")
    public ResponseEntity<ResponseResult<Product>> incrementStockProduct(@RequestBody UpdateProductStock data) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.incrementStockProduct(data));
    }
}
