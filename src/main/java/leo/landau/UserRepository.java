package leo.landau;

import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface UserRepository extends CrudRepository<User, Long> {

}
