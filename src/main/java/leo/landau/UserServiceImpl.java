package leo.landau;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import jakarta.inject.Singleton;

@Singleton
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getUsername(), userDto.getFirstname(), userDto.getLastname(), userDto.getEmail(), userDto.getPhone());
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto findUserById(Long userId) {
        return UserDto.toDto(userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return UserDto.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}


