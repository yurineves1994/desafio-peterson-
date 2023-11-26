package empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import empresa.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

  UserDetails findByLogin(String login);
}
