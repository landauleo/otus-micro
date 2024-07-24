package leo.landau;

import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPhone());
        userRepository.save(user);
        return userDto;
    }

    public Optional<UserDto> findUserById(Long userId) {
        return Optional.ofNullable(userRepository.findById(userId));
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId);
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

}


