package kata.academy.auth.service.impl;

import kata.academy.auth.dao.AccountDao;
import kata.academy.auth.exception.AccountIsNotEnabledException;
import kata.academy.auth.exception.EntityNotFoundException;
import kata.academy.auth.exception.TokenValidationException;
import kata.academy.auth.exception.WrongPasswordException;
import kata.academy.auth.model.dto.AccountClaimsResponseDto;
import kata.academy.auth.model.dto.AccountValidateRequestDto;
import kata.academy.auth.model.entity.Account;
import kata.academy.auth.service.TokenService;
import kata.academy.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final AccountDao accountDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String getToken(AccountValidateRequestDto dto) {
        Optional<Account> optionalAccount = accountDao.getAccountByUsername(dto.username());
        if (optionalAccount.isEmpty()) {
            throw new EntityNotFoundException(String.format("Пользователя с username#%s нет в базе данных", dto.username()));
        }
        Account account = optionalAccount.get();
        if (!passwordEncoder.matches(dto.password(), account.getPassword())) {
            throw new WrongPasswordException("Пароль неверный");
        }
        if (!account.getIsEnabled()) {
            throw new AccountIsNotEnabledException("Пользователь заблокирован");
        }
        return jwtUtil.generateToken(account);
    }

    @Override
    public AccountClaimsResponseDto validateToken(String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new TokenValidationException("Срок действия токена истек");
        }
        Map<String, Object> claims = jwtUtil.parseToken(token);
        return AccountClaimsResponseDto
                .builder()
                .accountId((Long) claims.get("accountId"))
                .role((String) claims.get("role"))
                .build();
    }
}
