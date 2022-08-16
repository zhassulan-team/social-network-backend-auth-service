package kata.academy.eurekaauthservice.rest.outer;

import kata.academy.eurekaauthservice.api.Data;
import kata.academy.eurekaauthservice.model.dto.UserDto;
import kata.academy.eurekaauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Void> register(@RequestBody @Valid UserDto dto) {
        userService.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Data<String>> authenticate(@RequestBody @Valid UserDto dto) {
        return ResponseEntity.ok(Data.of(userService.authenticate(dto)));
    }
}
