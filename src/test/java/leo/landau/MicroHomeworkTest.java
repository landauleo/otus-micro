package leo.landau;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
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

    @Inject
    UserRepository userRepository;

    @Test
    void testHello() {
        HttpRequest<String> request = HttpRequest.GET("/health");
        HttpResponse<Object> response = client.toBlocking().exchange(request);

        HttpStatus status = response.getStatus();

        assertNotNull(status);
        assertEquals(HttpStatus.OK, status);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPhone("1234567890");

        HttpRequest<User> request = HttpRequest.POST("/user", user)
                .contentType(MediaType.APPLICATION_JSON);
        HttpResponse<User> response = client.toBlocking().exchange(request, User.class);

        assertEquals(HttpStatus.CREATED, response.getStatus());

        // Если нужно дополнительно проверить данные, возвращаемые в ответе, это можно сделать здесь
        // Например, можно получить объект пользователя из ответа
        User savedUser = response.body();
        assertNotNull(savedUser);
    }

}
