package leo.landau.controller;

import javax.validation.constraints.NotNull;

import io.micrometer.core.annotation.Timed;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import leo.landau.model.UserDto;
import leo.landau.service.UserService;

@Tag(name = "user")
@Controller("/user")
public class UserController {

    @Inject
    private UserService userService;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Timed(value = "http.server.requests", histogram = true)
    @Post("/register")
    public HttpResponse<UserDto> register(@Body @NotNull UserDto user) {
        return HttpResponse.created(userService.registerUser(user));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/profile")
    public HttpResponse<UserDto> getProfile(Authentication authentication) {
        return HttpResponse.ok(UserDto.toDto(userService.findByUsername(authentication.getName())));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Timed(value = "http.server.requests", histogram = true)
    @Put("/profile/{userId}")
    public HttpResponse<UserDto> updateProfile(Authentication authentication,
                                               @PathVariable Long userId,
                                               @Body @NotNull UserDto user) {
        return HttpResponse.ok(userService.updateUser(userId, user, authentication));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Timed(value = "http.server.requests", histogram = true)
    @Get("/{userId}")
    public HttpResponse<UserDto> findUserById(@PathVariable Long userId) {
        return HttpResponse.ok(userService.findUserById(userId));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Timed(value = "http.server.requests", histogram = true)
    @Delete("/{userId}")
    public HttpResponse<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return HttpResponse.noContent();
    }

}


