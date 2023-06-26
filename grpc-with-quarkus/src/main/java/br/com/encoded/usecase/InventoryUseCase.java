package br.com.encoded.usecase;

import br.com.encoded.domain.entity.Inventory;
import br.com.encoded.repository.InventoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InventoryUseCase {

    @Inject
    private InventoryRepository inventoryRepository;

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.saveInventory(inventory);
    }

    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.updateInventory(inventory);
    }

    public Optional<Inventory> findById(Long id) {

        return inventoryRepository.findByInventoryId(id);
    }

    public List<Inventory> findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public List<Inventory> findAllInvetoryList() {
        return inventoryRepository.findAllInvetory();
    }

}
