package br.com.encode.service.impl;

import br.com.encode.domain.Product;
import br.com.encode.domain.dto.ProductInputDTO;
import br.com.encode.domain.dto.ProductOutputDTO;
import br.com.encode.exception.ProductAlreadyExistsException;
import br.com.encode.exception.ProductNotFoundException;
import br.com.encode.repository.ProductRepository;
import br.com.encode.service.ProductService;
import br.com.encode.util.ProductMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductOutputDTO createProduct(ProductInputDTO inputDTO) {

        checkDuplicity(inputDTO.getName());

        var product = ProductMapperUtil.createProductInputDtoToProduct(inputDTO);

        Product productCreated = repository.save(product);

        return ProductMapperUtil.productToProductOutputDto(productCreated);
    }

    @Override
    public ProductOutputDTO findProductById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.valueOf(id)));

        return ProductMapperUtil.productToProductOutputDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(
                findProductById(id).getId()
        );
    }

    @Override
    public ProductOutputDTO updateProduct(ProductInputDTO inputDTO) {

        this.findProductById(inputDTO.getId());

        return ProductMapperUtil.productToProductOutputDto(repository.save(
                ProductMapperUtil.updateProductInputDtoToProduct(inputDTO)
        ));
    }

    @Override
    public List<ProductOutputDTO> findAllProduct() {
        List<Product> products = repository.findAll();

        return ProductMapperUtil.productListToProductOutputDtoList(products);
    }

    private void checkDuplicity(String name){
        this.repository.findByNameIgnoreCase(name)
                .ifPresent(e -> {
                    throw new ProductAlreadyExistsException(name);
        });
    }
}
