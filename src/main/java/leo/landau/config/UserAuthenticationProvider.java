package leo.landau.config;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import jakarta.inject.Singleton;
import leo.landau.model.User;
import leo.landau.service.UserService;
import org.reactivestreams.Publisher;

@Singleton
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String username = (String) authenticationRequest.getIdentity();
        String password = (String) authenticationRequest.getSecret();

        return Flowable.create(emitter -> {
            User user = userService.findByUsername(username);

            if (user != null && passwordEncoder.matches(password, user.getHashedPassword())) {
                emitter.onNext(AuthenticationResponse.success(username));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }

}