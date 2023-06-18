package br.com.encode.controller;

import br.com.encode.EmptyRequest;
import br.com.encode.ProductRequest;
import br.com.encode.ProductResponse;
import br.com.encode.ProductResponseList;
import br.com.encode.RequestById;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Test
    public void create() {
        ProductRequest request = ProductRequest.newBuilder()
                .setName("Product 1")
                .setPrice(10.0)
                .setQuantityInStock(10).build();

        assertThat(request.getName()).isEqualTo("Product 1");
    }

    @Test
    public void findByid() {
        RequestById request = RequestById.newBuilder()
                .setId(1L)
                .build();

        assertThat(request.getId()).isEqualTo(1L);
    }

    @Test
    void findAllSuccess() {

        ProductResponseList responseList = ProductResponseList.newBuilder()
                .addProducts(ProductResponse.newBuilder()
                        .setId(1L)
                        .setName("Product 1")
                        .setPrice(10.0)
                        .setQuantityInStock(10).build())
                .addProducts(ProductResponse.newBuilder()
                        .setId(2L)
                        .setName("Product 2")
                        .setPrice(20.0)
                        .setQuantityInStock(20).build())
                .build();

        assertThat(responseList.getProductsCount()).isEqualTo(2);
        assertThat(responseList).isInstanceOf(ProductResponseList.class);
    }
}
