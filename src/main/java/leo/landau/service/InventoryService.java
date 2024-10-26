package leo.landau.service;

public interface InventoryService {

    boolean reserveItem(Long itemId, int quantity);

    void cancelReservation(Long itemId, int quantity);

}
