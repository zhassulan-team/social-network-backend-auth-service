package kata.academy.eurekaauthservice.service.impl;

import kata.academy.eurekaauthservice.model.dto.UserDto;
import kata.academy.eurekaauthservice.model.dto.UserValidateDto;
import kata.academy.eurekaauthservice.model.entity.User;
import kata.academy.eurekaauthservice.model.enums.Role;
import kata.academy.eurekaauthservice.repository.UserRepository;
import kata.academy.eurekaauthservice.service.UserService;
import kata.academy.eurekaauthservice.util.ApiValidationUtil;
import kata.academy.eurekaauthservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void register(UserDto userDto) {
        userRepository.save(
                User
                        .builder()
                        .email(userDto.email())
                        .password(passwordEncoder.encode(userDto.password()))
                        .role(Role.USER)
                        .build()
        );
    }

    @Override
    public String authenticate(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.email());
        ApiValidationUtil.requireTrue(optionalUser.isPresent(), String.format("User с email %s нет в базе данных", userDto.email()));
        User user = optionalUser.get();
        ApiValidationUtil.requireTrue(passwordEncoder.matches(userDto.password(), user.getPassword()), "Неверный пароль");
        user.setToken(jwtUtil.generateToken(user));
        userRepository.save(user);
        return user.getToken();
    }

    @Transactional(readOnly = true)
    @Override
    public UserValidateDto validate(String token) {
        return jwtUtil.parse(token);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }
}
