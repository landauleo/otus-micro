package leo.landau.service;

import javax.transaction.Transactional;

import jakarta.inject.Singleton;
import leo.landau.model.Delivery;
import leo.landau.repository.DeliveryRepository;

@Singleton
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public boolean reserveCourier(Long courierId) {
        Delivery delivery = new Delivery(courierId);
        try {
            deliveryRepository.save(delivery);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @Override
    public void cancelCourierReservation(Long courierId) {
        deliveryRepository.deleteById(courierId);
    }

}
