package br.com.encoded.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="inventory")
public class Inventory {

    @Id
    @SequenceGenerator(name = "inventorySeq", sequenceName = "inventory_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="inventorySeq")
    private Long id;
    private Long productId;
    private Long quantity;
    private BigDecimal cost;
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Inventory() {}

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public static class Builder {
        private Long id;
        private Long productId;
        private Long quantity;
        private BigDecimal cost;
        private LocalDateTime createDate;

        private LocalDateTime updateDate;

        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setQuantity(Long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setCost(BigDecimal cost) {
            this.cost = cost;
            return this;
        }

        public Builder setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder setUpdateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public Inventory build() {
            Inventory inventory = new Inventory();
            inventory.id = this.id;
            inventory.productId = this.productId;
            inventory.quantity = this.quantity;
            inventory.cost = this.cost;
            inventory.createDate = this.createDate;
            inventory.updateDate = this.updateDate;
            return inventory;
        }
    }
}
