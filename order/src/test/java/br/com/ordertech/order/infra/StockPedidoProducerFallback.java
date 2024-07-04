package br.com.ordertech.order.infra;

import br.com.ordertech.order.dto.ProductDto;
import br.com.ordertech.order.dto.UpdateProductStock;

import java.math.BigDecimal;

public class StockPedidoProducerFallback implements StockPedidoProducer{
    @Override
    public Long getQuantityProductById(Long productId) {
        return 0L; // Retorna 0 como fallback para quantidade
    }

    @Override
    public void removeStock(Long id, int quantity) {
        // Não faz nada no fallback
    }

    @Override
    public void reserveProduct(ProductDto product) {
        // Não faz nada no fallback
    }

    @Override
    public BigDecimal getPrice(Long id) {
        return BigDecimal.ZERO; // Retorna 0 como fallback para preço
    }

    @Override
    public void incrementStockProduct(UpdateProductStock product) {
        // Não faz nada no fallback
    }
}
