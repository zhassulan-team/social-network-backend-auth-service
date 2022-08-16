package kata.academy.auth.service;

import kata.academy.auth.model.dto.AccountClaimsResponseDto;
import kata.academy.auth.model.dto.AccountValidateRequestDto;

public interface TokenService {

    String getToken(AccountValidateRequestDto dto);

    AccountClaimsResponseDto validateToken(String token);
}
