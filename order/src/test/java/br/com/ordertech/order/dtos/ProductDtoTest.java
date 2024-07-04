package br.com.ordertech.order.dtos;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.ProductDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDtoTest {

    @Test
    void testAllArgsConstructor() {
        ProductDto dto = OrderHelper.buildProduct();
        assertThat(dto.getProductID()).isEqualTo(1);
        assertThat(dto.getName()).isEqualTo("Samsung A72 Dual SIM 128 GB very peri 8 GB RAM");
        assertThat(dto.getDescription()).isEqualTo("Cinco câmeras traseiras de 64 Mpx/13 Mpx, memória interna de 128GB...");
        assertThat(dto.getPrice().doubleValue()).isEqualTo(2450.0);
        assertThat(dto.getQuantityStock()).isEqualTo(100);
    }
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testNoArgsConstructor() {
        ProductDto productDto = new ProductDto();
        assertThat(productDto).isNotNull();
    }


    @Test
    public void testPartialConstructor() {
        ProductDto productDto = new ProductDto(1L, 20);

        assertThat(productDto.getProductID()).isEqualTo(1L);
        assertThat(productDto.getQuantityStock()).isEqualTo(20);
    }

    @Test
    public void testValidation() {
        ProductDto productDto = new ProductDto();

        Set<ConstraintViolation<ProductDto>> violations = validator.validate(productDto);

        assertThat(violations).hasSize(5);
    }
}
