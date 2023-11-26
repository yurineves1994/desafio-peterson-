package empresa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import empresa.entities.User;
import empresa.entities.enums.UserRole;
import empresa.repositories.UserRepository;

@Component
public class StartupDataLoader {

  @Autowired
  private UserRepository userRepository;

  @EventListener
  public void onApplicationEvent(ApplicationReadyEvent event) {
    String encryptedPassword = new BCryptPasswordEncoder().encode("12345");

    User newUser = new User("admin@admin.com", encryptedPassword, UserRole.ADMIN);

    this.userRepository.save(newUser);
  }
}
