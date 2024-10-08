package leo.landau.service;

import io.micronaut.security.authentication.Authentication;
import leo.landau.model.User;
import leo.landau.model.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto findUserById(Long userId);

    User findByUsername(String username);

    UserDto updateUser(Long userId, UserDto userDto, Authentication requester);

    void deleteUser(Long userId);

}
