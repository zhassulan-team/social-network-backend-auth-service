package kata.academy.eurekaauthservice.repository;

import kata.academy.eurekaauthservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    @Query("SELECT email FROM User WHERE id = :userId")
    String getEmailById(Long userId);
}
