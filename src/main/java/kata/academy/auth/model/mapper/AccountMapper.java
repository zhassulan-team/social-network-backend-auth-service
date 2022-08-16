package kata.academy.auth.model.mapper;

import kata.academy.auth.model.dto.AccountPersistRequestDto;
import kata.academy.auth.model.entity.Account;

public final class AccountMapper {

    private AccountMapper() {
    }

    public static Account toEntity(AccountPersistRequestDto dto) {
        return Account
                .builder()
                .username(dto.username())
                .password(dto.password())
                .build();
    }
}
