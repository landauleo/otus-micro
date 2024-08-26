package leo.landau;

import javax.validation.constraints.NotNull;

import io.micrometer.core.annotation.Timed;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

@Tag(name = "user")
@Controller("/user")
public class UserController {

    @Inject
    private UserServiceImpl userServiceImpl;

    @Timed(value = "http.server.requests", histogram = true)
    @Post
    public HttpResponse<UserDto> createUser(@Body @NotNull UserDto user) {
        return HttpResponse.ok(userServiceImpl.createUser(user));
    }

    @Timed(value = "http.server.requests", histogram = true)
    @Get("/{userId}")
    public HttpResponse<UserDto> findUserById(@PathVariable Long userId) {
        return HttpResponse.ok(userServiceImpl.findUserById(userId));
    }

    @Timed(value = "http.server.requests", histogram = true)
    @Put("/{userId}")
    public HttpResponse<UserDto> updateUser(@PathVariable Long userId, @Body @NotNull UserDto user) {
        return HttpResponse.ok(userServiceImpl.updateUser(userId, user));
    }

    @Timed(value = "http.server.requests", histogram = true)
    @Delete("/{userId}")
    public HttpResponse<Void> deleteUser(@PathVariable Long userId) {
        userServiceImpl.deleteUser(userId);
        return HttpResponse.noContent();
    }

}


