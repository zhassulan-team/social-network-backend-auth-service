package kata.academy.auth.service;

import kata.academy.auth.model.dto.AccountPersistRequestDto;

public interface AccountService {

    void persistAccount(AccountPersistRequestDto dto);
}
