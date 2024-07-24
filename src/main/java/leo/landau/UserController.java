package leo.landau;

import javax.validation.constraints.NotNull;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

@Tag(name = "user")
@Controller("/user")
public class UserController {

    @Inject
    private UserService userService;

    @Post
    public HttpResponse<UserDto> createUser(@Body @NotNull UserDto user) {
        return HttpResponse.ok(userService.createUser(user));
    }

    @Get("/{userId}")
    public HttpResponse<UserDto> findUserById(@PathVariable Long userId) {
        return HttpResponse.ok(userService.findUserById(userId));
    }

    @Put("/{userId}")
    public HttpResponse<UserDto> updateUser(@PathVariable Long userId, @Body @NotNull UserDto user) {
        return HttpResponse.ok(userService.updateUser(userId, user));
    }

    @Delete("/{userId}")
    public HttpResponse<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return HttpResponse.noContent();
    }

}


