package kata.academy.auth.dao;

import kata.academy.auth.model.entity.Account;

import java.util.Optional;

public interface AccountDao {

    void persistAccount(Account account);

    Optional<Account> getAccountByUsername(String username);
}
