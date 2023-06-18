package br.com.encode.controller;

import br.com.encode.EmptyRequest;
import br.com.encode.ProductRequest;
import br.com.encode.ProductResponse;
import br.com.encode.ProductResponseList;
import br.com.encode.ProductServiceGrpc;
import br.com.encode.RequestById;
import br.com.encode.domain.dto.ProductInputDTO;
import br.com.encode.domain.dto.ProductOutputDTO;
import br.com.encode.service.ProductService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GRpcService
public class ProductController extends ProductServiceGrpc.ProductServiceImplBase {

    @Autowired
    private ProductService service;

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        ProductInputDTO inputDTO = new ProductInputDTO(request.getName(),
                request.getPrice(), request.getQuantityInStock());

        ProductOutputDTO outputDTO = service.createProduct(inputDTO);

        ProductResponse productResponse = ProductResponse.newBuilder().setId(outputDTO.getId()).
                setName(outputDTO.getName()).
                setPrice(outputDTO.getPrice()).
                setQuantityInStock(outputDTO.getQuantityInStock()).build();

        responseObserver.onNext(productResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void findByID(RequestById request, StreamObserver<ProductResponse> responseObserver) {
        ProductOutputDTO outputDTO = service.findProductById(request.getId());

        ProductResponse productResponse = ProductResponse.newBuilder()
                .setId(outputDTO.getId()).
                setName(outputDTO.getName()).
                setPrice(outputDTO.getPrice()).
                setQuantityInStock(outputDTO.getQuantityInStock()).build();

        responseObserver.onNext(productResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(RequestById request, StreamObserver<ProductResponse> responseObserver) {
        service.deleteProduct(request.getId());

        responseObserver.onNext(ProductResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(EmptyRequest request, StreamObserver<ProductResponseList> responseObserver) {
        List<ProductOutputDTO> outputDTOList = service.findAllProduct();

        ProductResponseList.Builder builder = ProductResponseList.newBuilder()
                .addAllProducts(outputDTOList.stream()
                        .map(outputDTO -> ProductResponse.newBuilder()
                        .setId(outputDTO.getId())
                        .setName(outputDTO.getName())
                        .setPrice(outputDTO.getPrice())
                        .setQuantityInStock(outputDTO.getQuantityInStock())
                        .build()).toList());

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
