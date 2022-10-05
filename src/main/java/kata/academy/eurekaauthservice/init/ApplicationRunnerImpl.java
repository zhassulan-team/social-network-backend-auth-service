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
            String email = new StringBuilder("user").append(i).append("@").append("mail.ru").toString();
            User user = new User();
            user.setEmail(email);
            user.setPassword("user");
            userService.addUser(user);
        }
    }
}
