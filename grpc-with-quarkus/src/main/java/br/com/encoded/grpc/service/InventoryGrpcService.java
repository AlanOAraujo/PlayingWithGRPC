package br.com.encoded.grpc.service;

import br.com.encoded.CreateInventoryRequest;
import br.com.encoded.EmptyRequest;
import br.com.encoded.InventoryByProductId;
import br.com.encoded.InventoryResponse;
import br.com.encoded.InventoryServiceGrpc;
import br.com.encoded.ListInventoryResponse;
import br.com.encoded.RequestById;
import br.com.encoded.UpdateInventoryRequest;
import br.com.encoded.domain.dto.input.InventoryInputRecord;
import br.com.encoded.domain.dto.output.InventoryOutputRecord;
import br.com.encoded.usecase.InventoryUseCase;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;

import java.util.List;

@Blocking
@GrpcService
public class InventoryGrpcService extends InventoryServiceGrpc.InventoryServiceImplBase {

    @Inject
    private InventoryUseCase inventoryUseCase;

    @Override
    public void createInventory(CreateInventoryRequest request, StreamObserver<InventoryResponse> responseObserver) {

        InventoryInputRecord invInput = new InventoryInputRecord(null, request.getProductId(),
                request.getQuantity(), request.getCost(), "", false);

        InventoryOutputRecord outputRecord =
                InventoryOutputRecord.inventoryToInventoryOutput(
                        inventoryUseCase.createInventory(invInput.inventoryInputToInventory()));

        InventoryResponse response = InventoryResponse.newBuilder()
                .setInventoryId(outputRecord.id())
                .setProductId(outputRecord.productId())
                .setQuantity(outputRecord.quantity())
                .setCost(outputRecord.cost())
                .setCreateDate(outputRecord.createDate())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateInventory(UpdateInventoryRequest request, StreamObserver<InventoryResponse> responseObserver) {

        InventoryInputRecord invInput = new InventoryInputRecord(request.getInventoryId(), request.getProductId(),
                request.getQuantity(), request.getCost(), request.getCreateDate(), true);

        InventoryOutputRecord outputRecord =
                InventoryOutputRecord.inventoryToInventoryOutput(
                        inventoryUseCase.updateInventory(invInput.inventoryInputToInventory()));

        InventoryResponse response = InventoryResponse.newBuilder()
                .setInventoryId(outputRecord.id())
                .setProductId(outputRecord.productId())
                .setQuantity(outputRecord.quantity())
                .setCost(outputRecord.cost())
                .setCreateDate(outputRecord.createDate())
                .setUpdateDate(outputRecord.updateDate())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findByInventoryId(RequestById request, StreamObserver<InventoryResponse> responseObserver) {
        try {
            InventoryOutputRecord outputRecord = InventoryOutputRecord.inventoryToInventoryOutput(
                    inventoryUseCase.findById(request.getId()));

            InventoryResponse inventoryResponse = InventoryResponse.newBuilder()
                    .setInventoryId(outputRecord.id())
                    .setProductId(outputRecord.productId())
                    .setQuantity(outputRecord.quantity())
                    .setCost(outputRecord.cost())
                    .setCreateDate(outputRecord.createDate())
                    .setUpdateDate(outputRecord.updateDate())
                    .build();

            responseObserver.onNext(inventoryResponse);
            responseObserver.onCompleted();
        } catch (RuntimeException ie) {
            responseObserver.onError(ie.getCause());
        }
    }

    @Override
    public void findInventoryByProductId(InventoryByProductId request, StreamObserver<ListInventoryResponse> responseObserver) {

        List<InventoryOutputRecord> outputRecordList =
                InventoryOutputRecord.inventoryListToInventoryOutputList(
                        inventoryUseCase.findByProductId(request.getId()));

        ListInventoryResponse.Builder listInventoryResponse = ListInventoryResponse.newBuilder()
                .addAllInventories(outputRecordList.stream()
                        .map(outputRecord -> InventoryResponse.newBuilder()
                                .setInventoryId(outputRecord.id())
                                .setProductId(outputRecord.productId())
                                .setQuantity(outputRecord.quantity())
                                .setCost(outputRecord.cost())
                                .setCreateDate(outputRecord.createDate())
                                .setUpdateDate(outputRecord.updateDate())
                                .build()).toList());

        responseObserver.onNext(listInventoryResponse.build());
        responseObserver.onCompleted();
    }

    @Override
    public void findAllInventoryList(EmptyRequest request, StreamObserver<ListInventoryResponse> responseObserver) {

        List<InventoryOutputRecord> outputRecordList = InventoryOutputRecord.inventoryListToInventoryOutputList(
                inventoryUseCase.findAllInvetoryList());

        ListInventoryResponse.Builder listInventoryResponse = ListInventoryResponse.newBuilder()
                .addAllInventories(outputRecordList.stream()
                        .map(outputRecord -> InventoryResponse.newBuilder()
                                .setInventoryId(outputRecord.id())
                                .setProductId(outputRecord.productId())
                                .setQuantity(outputRecord.quantity())
                                .setCost(outputRecord.cost())
                                .setCreateDate(outputRecord.createDate())
                                .setUpdateDate(outputRecord.updateDate())
                                .build()).toList());

        responseObserver.onNext(listInventoryResponse.build());
        responseObserver.onCompleted();

    }
}
