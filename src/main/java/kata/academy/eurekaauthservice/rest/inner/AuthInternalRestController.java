package kata.academy.eurekaauthservice.rest.inner;

import kata.academy.eurekaauthservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/internal/v1/auth")
public class AuthInternalRestController {

    private final UserRepository userRepository;

    @GetMapping("/{followedId}")
    public ResponseEntity<Boolean> existsById(@PathVariable @Positive Long followedId) {
        return ResponseEntity.ok(userRepository.existsById(followedId));
    }
}
