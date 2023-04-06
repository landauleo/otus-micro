package leo.landau;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class MicroHomeworkTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testHello() {
        HttpRequest<String> request = HttpRequest.GET("/health");
        HttpResponse<Object> response = client.toBlocking().exchange(request);

        HttpStatus status = response.getStatus();

        assertNotNull(status);
        assertEquals(HttpStatus.OK, status);
    }

}
