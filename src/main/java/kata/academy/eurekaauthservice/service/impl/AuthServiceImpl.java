package kata.academy.eurekaauthservice.service.impl;

import kata.academy.eurekaauthservice.model.entity.User;
import kata.academy.eurekaauthservice.repository.AuthRepository;
import kata.academy.eurekaauthservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    @Override
    public User addUser(User user) {
        return authRepository.save(user);
    }
}
