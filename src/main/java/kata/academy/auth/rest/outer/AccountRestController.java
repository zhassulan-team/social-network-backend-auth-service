package kata.academy.auth.rest.outer;

import kata.academy.auth.model.dto.AccountPersistRequestDto;
import kata.academy.auth.service.AccountService;
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
@RequestMapping("/api/auth/v1/account")
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping("/registration")
    public ResponseEntity<Void> persistAccount(@RequestBody @Valid AccountPersistRequestDto dto) {
        accountService.persistAccount(dto);
        return ResponseEntity.ok().build();
    }
}
