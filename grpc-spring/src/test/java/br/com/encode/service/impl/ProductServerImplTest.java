package br.com.encode.service.impl;

import br.com.encode.domain.Product;
import br.com.encode.domain.dto.ProductInputDTO;
import br.com.encode.domain.dto.ProductOutputDTO;
import br.com.encode.exception.ProductAlreadyExistsException;
import br.com.encode.exception.ProductNotFoundException;
import br.com.encode.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

@ExtendWith(MockitoExtension.class)
public class ProductServerImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Flyway flyway;

    @BeforeEach
    public void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void creatProductSuccessTest() {
        Product product = new Product(1L, "productName", 10.00, 10);

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductInputDTO inputDTO = new ProductInputDTO("productName", 10.00, 10);

        ProductOutputDTO outputDTO = productService.createProduct(inputDTO);

        Assertions.assertThat(outputDTO)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }

    @Test
    public void creatProductExceptionTest() {
        Product product = new Product(1L, "productName", 10.00, 10);

        Mockito.when(productRepository.findByNameIgnoreCase(Mockito.any())).thenReturn(Optional.of(product));

        ProductInputDTO inputDTO = new ProductInputDTO("productName", 10.00, 10);

        assertThatExceptionOfType(ProductAlreadyExistsException.class)
                .isThrownBy(() -> productService.createProduct(inputDTO));

    }

    @Test
    public void findByIdSuccessTest() {
        Product product = new Product(1L, "productName", 10.00, 10);

        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(product));

        ProductOutputDTO outputDTO = productService.findProductById(1L);

        Assertions.assertThat(outputDTO)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }

    @Test
    public void findByIfProductNotFoundTest() {
        Mockito.when(productRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> productService.findProductById(20L));
    }

    @Test
    public void deleteProductSuccessTest() {
        Product product = new Product(1L, "productName", 10.00, 10);

        Mockito.when(productRepository.
                findById(Mockito.any()))
                .thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        assertThatNoException().isThrownBy(() -> productService.deleteProduct(1L));

    }

    @Test
    public void deleteProductNotFoundTest() {
        Mockito.when(productRepository.
                findById(Mockito.any()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> productService.deleteProduct(20L));
    }

    @Test
    public void findAllProductSuccessTest() {
        Product product = new Product(1L, "productName", 10.00, 10);

        Mockito.when(productRepository
                .findAll())
                .thenReturn(List.of(product));

        Assertions.assertThat(productService.findAllProduct())
                .usingRecursiveComparison()
                .isEqualTo(java.util.List.of(product));
    }

}
