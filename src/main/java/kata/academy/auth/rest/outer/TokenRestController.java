package kata.academy.auth.rest.outer;

import kata.academy.auth.api.Data;
import kata.academy.auth.model.dto.AccountValidateRequestDto;
import kata.academy.auth.service.TokenService;
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
@RequestMapping("/api/auth/v1/token")
public class TokenRestController {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<Data<String>> getToken(@RequestBody @Valid AccountValidateRequestDto dto) {
        return ResponseEntity.ok(Data.of(tokenService.getToken(dto)));
    }
}
