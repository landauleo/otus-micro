package leo.landau;

import io.micronaut.security.authentication.Authentication;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto findUserById(Long userId);

    User findByUsername(String username);

    UserDto updateUser(Long userId, UserDto userDto, Authentication requester);

    void deleteUser(Long userId);

}
