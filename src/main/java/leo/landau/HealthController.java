package leo.landau;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/health")
public class HealthController {

    @Inject
    BookRepository bookRepository;

    @Get
    public HttpResponse<HealthDto> index() {
        Book book = new Book();
        book.setTitle("The Stand");
        book.setPages(1000);
        bookRepository.save(book);

        return HttpResponse.ok().body(new HealthDto("OK"));
    }

}