package br.com.encode.service;

import br.com.encode.domain.dto.ProductInputDTO;
import br.com.encode.domain.dto.ProductOutputDTO;

import java.util.List;

public interface ProductService {

    ProductOutputDTO createProduct(ProductInputDTO inputDTO);
    ProductOutputDTO findProductById(Long id);
    void deleteProduct(Long id);
    ProductOutputDTO updateProduct(ProductInputDTO inputDTO);
    List<ProductOutputDTO> findAllProduct();
}
