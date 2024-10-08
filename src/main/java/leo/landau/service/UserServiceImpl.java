package leo.landau.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthorizationException;
import jakarta.inject.Singleton;
import leo.landau.config.BCryptPasswordEncoder;
import leo.landau.model.User;
import leo.landau.model.UserDto;
import leo.landau.UserRepository;

@Singleton
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private AccountService accountService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setHashedPassword(hashedPassword);

        accountService.createAccount(user.getId());

        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto findUserById(Long userId) {
        return UserDto.toDto(userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto, Authentication requester) {
        checkAccess(userId, requester);
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

    private void checkAccess(Long requestedUserId, Authentication requester) {
        if (!findByUsername(requester.getName()).getId().equals(requestedUserId)) {
            throw new AuthorizationException(requester);
        }
    }

}


