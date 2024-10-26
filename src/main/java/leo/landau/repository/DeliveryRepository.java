package leo.landau.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import leo.landau.model.Delivery;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    void deleteById(Long id);

}
