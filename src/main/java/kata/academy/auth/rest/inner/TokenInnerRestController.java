package kata.academy.auth.rest.inner;

import kata.academy.auth.model.dto.AccountClaimsResponseDto;
import kata.academy.auth.service.TokenService;
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
@RequestMapping("/api/inner/v1/token")
public class TokenInnerRestController {

    private final TokenService tokenService;

    @PostMapping("/validation")
    public ResponseEntity<AccountClaimsResponseDto> validateToken(@RequestParam @NotBlank String token) {
        return ResponseEntity.ok(tokenService.validateToken(token));
    }
}
