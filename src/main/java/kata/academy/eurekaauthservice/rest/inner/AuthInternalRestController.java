package kata.academy.eurekaauthservice.rest.inner;

import kata.academy.eurekaauthservice.model.dto.UserValidateDto;
import kata.academy.eurekaauthservice.service.UserService;
import kata.academy.eurekaauthservice.util.ApiValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/internal/v1/auth")
public class AuthInternalRestController {

    private final UserService userService;


    @PostMapping("/validate")
    public ResponseEntity<UserValidateDto> validate(@RequestParam @NotBlank String token) {
        return ResponseEntity.ok(userService.validate(token));
    }

    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable @Positive Long userId) {
        return ResponseEntity.ok(userService.existsById(userId));
    }

    @GetMapping("/{userId}/email")
    public ResponseEntity<String> getEmailById(@PathVariable @Positive Long userId) {
        ApiValidationUtil.requireTrue(userService.existsById(userId), "Пользователя с таким id не существует!");
        return ResponseEntity.ok(userService.getEmailById(userId));
    }
}
