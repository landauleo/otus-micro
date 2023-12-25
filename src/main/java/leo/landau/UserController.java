package leo.landau;

import java.util.Optional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

@Controller("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Post
    public HttpResponse<User> createUser(@Body User user) {
        User savedUser = userRepository.save(user);
        return HttpResponse.created(savedUser);
    }

    @Get("/{userId}")
    public HttpResponse<User> getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return HttpResponse.ok(optionalUser.get());
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{userId}")
    public HttpResponse<Void> deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

    @Put("/{userId}")
    public HttpResponse<Void> updateUser(Long userId, @Body User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Обновление полей пользователя
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());

            userRepository.save(existingUser);
            return HttpResponse.ok();
        } else {
            return HttpResponse.notFound();
        }
    }

}
