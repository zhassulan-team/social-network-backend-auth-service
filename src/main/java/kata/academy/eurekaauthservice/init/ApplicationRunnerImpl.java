package kata.academy.eurekaauthservice.init;

import kata.academy.eurekaauthservice.model.entity.User;
import kata.academy.eurekaauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Profile("dev")
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 1; i <= 30; i++) {
            User user = new User();
            user.setEmail("user" + i + "@mail.ru");
            user.setPassword("user");
            userService.addUser(user);
        }
    }
}
