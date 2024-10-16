package leo.landau.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import leo.landau.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
