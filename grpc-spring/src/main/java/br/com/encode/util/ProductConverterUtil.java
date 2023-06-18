package br.com.encode.util;

import br.com.encode.domain.Product;
import br.com.encode.domain.dto.ProductInputDTO;
import br.com.encode.domain.dto.ProductOutputDTO;

import java.util.List;

public class ProductConverterUtil {

    public static ProductOutputDTO productToProductOutputDto(Product product){

        return new ProductOutputDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantityInStock());

    }

    public static Product productInputDtoToProduct(ProductInputDTO product){

        return new Product(
                null,
                product.getName(),
                product.getPrice(),
                product.getQuantityInStock());

    }

    public static List<ProductOutputDTO> productListToProductOutputDtoList(List<Product> products) {

        return products.stream()
            .map(ProductConverterUtil::productToProductOutputDto)
            .toList();

    }
}
