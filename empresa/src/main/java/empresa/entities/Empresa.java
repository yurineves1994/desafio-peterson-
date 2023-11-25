package empresa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "tb_empresa")
@Table(name = "tb_empresa")
@Data
public class Empresa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "razao_social")
  @NotBlank(message = "Razão Social é obrigatória")
  private String razaoSocial;

  @Column(name = "nome_fantasia")
  @NotBlank(message = "Nome Fantasia é obrigatório")
  private String nomeFantasia;

  @NotBlank(message = "E-mail é obrigatório")
  @Email(message = "E-mail inválido")
  private String email;

  @NotBlank(message = "CNPJ é obrigatório")
  @Pattern(regexp = "\\d{14}", message = "CNPJ inválido")
  private String cnpj;

  @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Endereco> enderecos = new ArrayList<>();

}
