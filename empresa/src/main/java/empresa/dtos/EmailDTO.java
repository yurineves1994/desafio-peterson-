package empresa.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class EmailDTO {
  private String emailEmpresa;
  private String emailPessoa;
  private String assunto;
  private String mensagem;
  private String nomePessoa;
}
