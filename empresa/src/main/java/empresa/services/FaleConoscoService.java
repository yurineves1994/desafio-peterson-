package empresa.services;

import org.springframework.stereotype.Service;

import empresa.entities.Empresa;
import empresa.entities.FaleConosco;
import empresa.repositories.EmpresaRepository;
import empresa.repositories.FaleConoscoRepository;
import empresa.services.exceptions.DataIntegratyViolationException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FaleConoscoService {

    private FaleConoscoRepository faleConoscoRepository;
    private EmpresaRepository empresaRepository;

    private EmailService emailService;

    public FaleConosco cadastrarFaleConosco(Long idEmpresa, FaleConosco pergunta) {
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new DataIntegratyViolationException("Empresa não foi Localizada!"));

        String emailEmpresa = empresa.getEmail();
        String emailPessoa = empresa.getEmail();
        String assunto = pergunta.getAssunto();
        String mensagem = pergunta.getMensagem();
        String nomePessoa = pergunta.getNomePessoa();
        emailService.enviarApiMensageria(emailEmpresa, emailPessoa, assunto, mensagem, nomePessoa);

        pergunta.setEmpresa(empresa);

        return faleConoscoRepository.save(pergunta);
    }
}
