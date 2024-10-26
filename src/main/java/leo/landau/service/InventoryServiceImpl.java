package leo.landau.service;

import javax.transaction.Transactional;

import jakarta.inject.Singleton;
import leo.landau.model.Item;
import leo.landau.repository.ItemRepository;

@Singleton
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final ItemRepository itemRepository;

    public InventoryServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public boolean reserveItem(Long itemId, int quantity) {
        Item item = itemRepository.findById(itemId).get();
        if (item.getQuantity().compareTo((long) quantity) >= 0) {
            item.setQuantity(Math.subtractExact(item.getQuantity(), (quantity)));
            itemRepository.save(item);
            return true;
        }
        return false;
    }

    @Override
    public void cancelReservation(Long itemId, int quantity) {
        Item item = itemRepository.findById(itemId).get();
        if (item.getQuantity().compareTo((long) quantity) >= 0) {
            item.setQuantity(Math.addExact(item.getQuantity(), (quantity)));
            itemRepository.save(item);
        }
    }

}
