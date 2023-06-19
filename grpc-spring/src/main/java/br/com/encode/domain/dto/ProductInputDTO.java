package br.com.encode.domain.dto;

public class ProductInputDTO {

    private final Long id;
    private final String name;
    private final Double price;
    private final Integer quantityInStock;

    public ProductInputDTO(String name, Double price, Integer quantityInStock) {
        this.id = null;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public ProductInputDTO(Long id, String name, Double price, Integer quantityInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }
}
