package empresa.dtos;

import empresa.entities.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
  
}
