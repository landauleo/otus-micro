package leo.landau;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;


@Repository
interface UserRepository extends CrudRepository<User, Long> {

}
