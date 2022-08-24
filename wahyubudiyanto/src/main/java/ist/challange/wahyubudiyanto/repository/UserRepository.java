package ist.challange.wahyubudiyanto.repository;

import ist.challange.wahyubudiyanto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByPassword(String password);

    @Query("SELECT u.password FROM User u WHERE u.id = ?1")
    String getPasswordById(Long id);

    @Query("SELECT u.username FROM User u Where u.id = ?1")
    String getUsernameById(Long id);
}
