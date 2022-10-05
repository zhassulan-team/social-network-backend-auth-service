package kata.academy.eurekaauthservice.repository;

import kata.academy.eurekaauthservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
