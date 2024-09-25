package leo.landau;

import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
