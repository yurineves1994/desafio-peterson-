package apimensageria.apiemail.entities;

import java.time.LocalDateTime;

import apimensageria.apiemail.entities.enums.StatusSend;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "tb_emails")
@Table(name = "tb_emails")
public class EmailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private String emailEmpresa;

  private String emailPessoa;

  private String assunto;

  @Column(columnDefinition = "TEXT")
  private String mensagem;

   private String nomePessoa;

  private LocalDateTime sendDateEmail;

  private StatusSend statusSend;

}
