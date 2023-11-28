package empresa.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import empresa.controllers.AuthenticationController;
import empresa.entities.User;
import empresa.entities.enums.UserRole;
import empresa.repositories.UserRepository;

@Component
public class StartupDataLoader {
  private static final Logger logger = LogManager.getLogger(StartupDataLoader.class);

  @Autowired
  private UserRepository userRepository;

  @EventListener
  public void onApplicationEvent(ApplicationReadyEvent event) {
    logger.info("USUARIO ADMIN CRIADO AO INICIAR APLICAÇÃO");
    String encryptedPassword = new BCryptPasswordEncoder().encode("12345");
    User newUser = new User("admin@admin.com", encryptedPassword, UserRole.ADMIN);

    this.userRepository.save(newUser);
  }
}
