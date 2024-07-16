package br.com.ordertech.order.infra;

import br.com.ordertech.order.dto.ProductDto;
import br.com.ordertech.order.dto.UpdateProductStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "stock", url="${URL_PRODUCT}")  //"${stock.url}")
public interface StockPedidoProducer {

    @GetMapping("/find/id")
    Long getQuantityProductById(Long productId);

    @GetMapping
    List<ProductDto> getAll();

    @PutMapping(value = "/atualizar/estoque/{id}/{quantity}")
    void removeStock(@PathVariable Long id,
                     @PathVariable int quantity);

    @PostMapping("/reserve")
    void reserveProduct(@RequestBody ProductDto product);

    @GetMapping(value = "/{id}/price")
    BigDecimal getPrice (@PathVariable Long id);

    @PatchMapping("/update/stock")
    void incrementStockProduct(@RequestBody UpdateProductStock product);

}
