package br.com.encoded.domain.dto.output;

import br.com.encoded.domain.entity.Inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public record InventoryOutputRecord(Long id, Long productId, Long quantity, Double cost, String createDate, String updateDate) {

    public static InventoryOutputRecord inventoryToInventoryOutput(Inventory inventory){

        return new InventoryOutputRecord(inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getCost().doubleValue(),
                formatLocalDateTime(inventory.getCreateDate()),
                formatLocalDateTime(inventory.getUpdateDate()));

    }

    public static List<InventoryOutputRecord> inventoryListToInventoryOutputList(List<Inventory> inventoryList){
        return inventoryList.stream()
                .map(InventoryOutputRecord::inventoryToInventoryOutput)
                .toList();
    }

    private static String formatLocalDateTime(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime)
                .map(ldt -> ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .orElse("");
    }

}