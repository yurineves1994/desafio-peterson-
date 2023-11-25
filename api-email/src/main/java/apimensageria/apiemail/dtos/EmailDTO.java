package apimensageria.apiemail.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank @Email String emailEmpresa,
        @NotBlank @Email String emailPessoa,
        @NotBlank String assunto,
        @NotBlank String mensagem,
        @NotBlank String nomePessoa) {
}
