//package leo.landau;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.Test;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//@MicronautTest
//@Testcontainers
//class YourTest {
//
//    @Container
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.2")
//            .withDatabaseName("test")
//            .withUsername("user")
//            .withPassword("password");
//
//    @Inject
//    BookRepository bookRepository;
//
//    @Test
//    @Transactional
//    void testSomething() {
//        Book book = new Book();
//        book.setTitle("The Stand");
//        book.setPages(1000);
//        bookRepository.save(book);
//    }
//}