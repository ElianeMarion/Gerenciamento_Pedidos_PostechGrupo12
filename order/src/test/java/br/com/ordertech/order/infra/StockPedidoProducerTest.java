package br.com.ordertech.order.infra;

import br.com.ordertech.order.exceptions.InvalidValueException;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {StockPedidoProducerFallback.class})
public class StockPedidoProducerTest {
    @Mock
    private StockPedidoProducer stockPedidoProducer;

    @InjectMocks
    private StockPedidoProducerFallback stockPedidoProducerFallback;

    @Test
    public void testGetQuantityProductById_Success() {

        when(stockPedidoProducer.getQuantityProductById(anyLong())).thenReturn(10L);

        Long result = stockPedidoProducer.getQuantityProductById(1L);

        assertThat(result).isNotNull().isEqualTo(10L);
    }

    @Test
    public void testGetPrice_Success() {

        when(stockPedidoProducer.getPrice(anyLong())).thenReturn(BigDecimal.TEN);

        BigDecimal result = stockPedidoProducer.getPrice(1L);

        assertThat(result).isNotNull().isEqualTo(BigDecimal.TEN);
    }
}
