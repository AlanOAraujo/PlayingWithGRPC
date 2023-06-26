package br.com.encode.util;

import br.com.encode.domain.Product;
import br.com.encode.domain.dto.ProductInputDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductMapperUtilTest {

    @Test
    public void productToProductOutputDtoTest(){
        var product = new Product(1L, "product Test", 10.00, 10);
        var productOutputDto = ProductMapperUtil.productToProductOutputDto(product);

        Assertions.assertThat(product)
                .usingRecursiveComparison()
                .isEqualTo(productOutputDto);
    }

    @Test
    public void productInputToProductTest(){
        var productInput = new ProductInputDTO("product Test", 10.00, 10);

        var product = ProductMapperUtil.createProductInputDtoToProduct(productInput);

        Assertions.assertThat(productInput)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }

    @Test
    public void productUpdateInputToProductTest(){
        var productInput = new ProductInputDTO(1L, "product Test", 10.00, 10);

        var product = ProductMapperUtil.updateProductInputDtoToProduct(productInput);

        Assertions.assertThat(productInput)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }
}
