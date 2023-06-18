//package br.com.encode.controller;
//
//import br.com.encode.ProductRequest;
//import br.com.encode.ProductResponse;
//import br.com.encode.ProductServiceGrpc;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.lognet.springboot.grpc.autoconfigure.OnGrpcServerEnabled;
//import java.util.concurrent.TimeUnit;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@OnGrpcServerEnabled
public class ProductControllerIT {
//
//    private ManagedChannel channel;
//
//    @BeforeEach
//    public void init() {
//        channel = ManagedChannelBuilder.forAddress("localhost", 9001).usePlaintext().build();
//    }
//
//    @AfterEach
//    public void cleanup() throws InterruptedException {
//        channel.shutdown();
//        channel.awaitTermination(10, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void create() {
//        ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);
//
//        ProductResponse response = stub.create(ProductRequest.newBuilder()
//                .setName("Product 1")
//                .setPrice(10.0)
//                .setQuantityInStock(10).build());
//
//        assertThat(response.getName()).isEqualTo("Product 1");
//    }
//
}
