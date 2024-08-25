package leo.landau;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto findUserById(Long userId);

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

}
