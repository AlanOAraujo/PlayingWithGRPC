package br.com.encoded.domain.dto.input;

import br.com.encoded.domain.entity.Inventory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public record InventoryInputRecord(Long id, Long productId, Long quantity, Double cost, String registryDate, boolean isUpdate) {

    public Inventory inventoryInputToInventory() {
        return new Inventory.Builder()
                .setId(this.id)
                .setProductId(this.productId)
                .setQuantity(this.quantity)
                .setCost(BigDecimal.valueOf(this.cost))
                .setCreateDate(createDateformatStringToLocalDateTime(this.registryDate))
                .setUpdateDate(updateDateformat(this.isUpdate))
                .build();
    }

    private static LocalDateTime createDateformatStringToLocalDateTime(String stringDate) {
        return Optional.of(stringDate).filter(s -> !s.isEmpty())
                    .map(strDate -> LocalDateTime.parse(strDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .orElseGet(() -> ZonedDateTime.now().toLocalDateTime());

    }

    private static LocalDateTime updateDateformat(boolean isUpdate) {
        return Optional.of(isUpdate).filter(b -> b)
                .map(b -> ZonedDateTime.now().toLocalDateTime())
                .orElse(null);
    }
}