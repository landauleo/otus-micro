package leo.landau;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
interface BookRepository extends JpaRepository<Book, Long> {

    @Executable
    Book find(String title);

}