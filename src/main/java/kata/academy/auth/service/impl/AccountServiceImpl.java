package kata.academy.auth.service.impl;

import kata.academy.auth.dao.AccountDao;
import kata.academy.auth.exception.EntityNotFoundException;
import kata.academy.auth.feign.ContentFeignClient;
import kata.academy.auth.model.dto.AccountPersistRequestDto;
import kata.academy.auth.model.dto.ProfilePersistRequestDto;
import kata.academy.auth.model.entity.Account;
import kata.academy.auth.model.enums.Role;
import kata.academy.auth.model.mapper.AccountMapper;
import kata.academy.auth.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final PasswordEncoder passwordEncoder;
    private final ContentFeignClient contentFeignClient;

    @Transactional
    @Override
    public void persistAccount(AccountPersistRequestDto dto) {
        Account account = AccountMapper.toEntity(dto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.USER);
        account.setRegistrationDate(LocalDateTime.now());
        account.setIsEnabled(Boolean.TRUE);
        accountDao.persistAccount(account);
        Optional<Account> optionalAccount = accountDao.getAccountByUsername(account.getUsername());
        if (optionalAccount.isEmpty()) {
            throw new EntityNotFoundException(String.format("Пользователя с username#%s нет в базе данных", account.getUsername()));
        }
        Account accountFromDao = optionalAccount.get();
        contentFeignClient.persistProfile(
                ProfilePersistRequestDto
                        .builder()
                        .accountId(accountFromDao.getId())
                        .username(accountFromDao.getUsername())
                        .name(dto.name())
                        .build()
        );
    }
}
