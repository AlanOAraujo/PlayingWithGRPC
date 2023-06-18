package br.com.encoded.repository;

import br.com.encoded.domain.entity.Inventory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class InventoryRepository implements PanacheRepository<Inventory> {

    @Transactional
    public Inventory saveInventory(Inventory inventory) {

        persistAndFlush(inventory);

        return inventory;
    }

    public Inventory updateInventory(Inventory inventory) {

        update("cost = ?1, quantity = ?2, updateDate = ?3 where id = ?4",
                inventory.getCost(),
                inventory.getQuantity(),
                inventory.getUpdateDate(),
                inventory.getId());

        return inventory;
    }

    public Inventory findByInventoryId(Long id) {
        return findById(id);
    }

    public List<Inventory> findByProductId(Long productId) {
        return list("productId", productId);
    }

    public List<Inventory> findAllInvetory() {
        return listAll();
    }
}
