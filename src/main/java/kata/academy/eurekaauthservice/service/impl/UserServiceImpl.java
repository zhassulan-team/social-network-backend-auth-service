package kata.academy.eurekaauthservice.service.impl;

import kata.academy.eurekaauthservice.model.entity.User;
import kata.academy.eurekaauthservice.repository.UserRepository;
import kata.academy.eurekaauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
