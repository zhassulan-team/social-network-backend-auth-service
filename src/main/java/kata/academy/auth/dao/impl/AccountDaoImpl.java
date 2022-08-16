package kata.academy.auth.dao.impl;

import kata.academy.auth.dao.AccountDao;
import kata.academy.auth.model.entity.Account;
import kata.academy.auth.util.JpaUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persistAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return JpaUtil.getSingleResultOrNull(
                entityManager.createQuery(
                                """
                                        SELECT a
                                        FROM Account a
                                        WHERE a.username = :username
                                        """, Account.class)
                        .setParameter("username", username)
        );
    }
}
