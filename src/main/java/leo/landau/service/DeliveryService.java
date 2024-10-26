package leo.landau.service;

public interface DeliveryService {

    boolean reserveCourier(Long courierId);

    void cancelCourierReservation(Long courierId);

}
