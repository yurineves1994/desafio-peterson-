package apimensageria.apiemail.entities;

import java.time.LocalDateTime;
import java.util.List;

import apimensageria.apiemail.entities.enums.StatusSend;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "tb_emails")
@Table(name = "tb_emails")
public class EmailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  @NotBlank(message = "E-mail é obrigatório")
  @Email(message = "E-mail inválido")
  private String emailEmpresa;

  @NotBlank(message = "E-mail é obrigatório")
  @Email(message = "E-mail inválido")
  private String emailPessoa;

  @NotBlank(message = "Assunto é obrigatória")
  private String assunto;

  @Column(columnDefinition = "TEXT")
  private String mensagem;

  private String nomePessoa;

  private LocalDateTime sendDateEmail;

  private StatusSend statusSend;
}
