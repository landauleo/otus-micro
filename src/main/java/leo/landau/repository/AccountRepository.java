package leo.landau.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import leo.landau.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUserId(Long userId);
}
