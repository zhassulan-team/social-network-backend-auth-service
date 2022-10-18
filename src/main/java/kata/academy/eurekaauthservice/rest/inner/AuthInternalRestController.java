package kata.academy.eurekaauthservice.rest.inner;

import kata.academy.eurekaauthservice.model.dto.UserValidateDto;
import kata.academy.eurekaauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

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
}
