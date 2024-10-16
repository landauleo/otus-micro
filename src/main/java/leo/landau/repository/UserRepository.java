package leo.landau.repository;

import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import leo.landau.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}
