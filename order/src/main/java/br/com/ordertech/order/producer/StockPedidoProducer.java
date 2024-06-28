package br.com.ordertech.order.producer;

import br.com.ordertech.order.dto.ProductDto;
import br.com.ordertech.order.dto.UpdateProductStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(value = "stock", url="http://localhost:8082/product")  //"${stock.url}")
public interface StockPedidoProducer {

    @GetMapping("/find/id")
    Long getQuantityProductById(Long productId);
    @PutMapping(value = "/atualizar/estoque/{id}/{quantity}")
    void removeStock(@PathVariable Long id,
                     @PathVariable int quantity);

    @PostMapping("reserve")
    void reserveProduct(@RequestBody ProductDto product);
    @GetMapping(value = "/{id}")
    BigDecimal getPrice (@PathVariable Long id);

    @PatchMapping("/update/stock")
    void incrementStockProduct(@RequestBody UpdateProductStock product);

}
