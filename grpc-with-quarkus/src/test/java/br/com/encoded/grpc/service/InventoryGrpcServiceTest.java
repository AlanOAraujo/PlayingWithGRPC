package br.com.encoded.grpc.service;

import br.com.encoded.InventoryResponse;
import br.com.encoded.InventoryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@QuarkusTest
public class InventoryGrpcServiceTest {

    private ManagedChannel channel;

    @BeforeEach
    public void init() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9001).usePlaintext().build();
    }

    @AfterEach
    public void cleanup() throws InterruptedException {
        channel.shutdown();
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void createInventoryServiceBlockingStub() {
        InventoryServiceGrpc.InventoryServiceBlockingStub stub = InventoryServiceGrpc.newBlockingStub(channel);
        InventoryResponse inventoryResponse = stub.createInventory(br.com.encoded.CreateInventoryRequest.newBuilder()
                .setProductId(1)
                .setQuantity(10)
                .setCost(10.0)
                .build());

        Assertions.assertEquals(inventoryResponse.getCost(), 10.0);
    }
}
