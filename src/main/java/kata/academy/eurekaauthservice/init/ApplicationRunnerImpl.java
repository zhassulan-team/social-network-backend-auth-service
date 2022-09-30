package kata.academy.eurekaauthservice.init;

import kata.academy.eurekaauthservice.model.entity.User;
import kata.academy.eurekaauthservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
@Profile("dev")
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final AuthService authService;

    @Override
    public void run(ApplicationArguments args) {
        for (long i = 1; i <= 30; i++) {
            StringBuilder email = new StringBuilder("user");
            email.append(i).append("@").append("mail.ru");
            authService.addUser(new User(i, email.toString(), "user"));
        }
    }
}
