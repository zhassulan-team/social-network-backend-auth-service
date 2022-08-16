package kata.academy.eurekaauthservice.service;

import kata.academy.eurekaauthservice.model.dto.UserDto;
import kata.academy.eurekaauthservice.model.dto.UserValidateDto;
import kata.academy.eurekaauthservice.model.entity.User;

public interface UserService {

    void addUser(User user);

    void register(UserDto userDto);

    String authenticate(UserDto userDto);

    UserValidateDto validate(String token);

    boolean existsById(Long userId);
}
