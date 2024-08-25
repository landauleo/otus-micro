package leo.landau;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface UserRepository extends CrudRepository<User, Long> {

}
