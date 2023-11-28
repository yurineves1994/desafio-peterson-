package empresa.services;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import empresa.entities.Empresa;
import empresa.entities.Endereco;
import empresa.repositories.EmpresaRepository;
import empresa.repositories.EnderecoRepository;
import empresa.services.exceptions.DataIntegratyViolationException;

@Service
public class EnderecoService {

     private static final Logger logger = LogManager.getLogger(EnderecoService.class);

    @Value("${viacep.url}")
    private String viaCepUrl;

    private EnderecoRepository enderecoRepository;
    private EmpresaRepository empresaRepository;

    private final RestTemplate restTemplate;

    public EnderecoService(EnderecoRepository enderecoRepository, EmpresaRepository empresaRepository,
            RestTemplate restTemplate) {
        this.enderecoRepository = enderecoRepository;
        this.empresaRepository = empresaRepository;
        this.restTemplate = restTemplate;
    }

    public boolean validarCep(String cep) {
        cep = cep.replaceAll("[^\\d]", "");

        String apiUrl = viaCepUrl + cep + "/json";

        try {
            Map<String, Object> responseData = restTemplate.getForObject(apiUrl, Map.class);

            if (responseData != null && responseData.containsKey("erro")) {
                logger.error("O CEP É INVALIDO!");
                return false;
            }
        } catch (Exception e) {
            logger.error("OCORREU ERRO NA REQUISIÇÃO NA API DO CEP");
            return false;
        }
        return true;
    }

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco cadastrarEndereco(Long idEmpresa, Endereco endereco) {

        if (!validarCep(endereco.getCep())) {
            logger.error("Esse CEP é inválido, tente outro!");
            throw new DataIntegratyViolationException("Esse CEP é inválido, tente outro!");
        }

        if (enderecoRepository.existsByCep(endereco.getCep())) {
            logger.error("Esse CEP já está cadastrado, tente outro!");
            throw new DataIntegratyViolationException("Esse CEP já está cadastrado, tente outro!");
        }

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new DataIntegratyViolationException("Empresa não foi localizada, tente outra!"));

        endereco.setEmpresa(empresa);
        logger.info("ENDEREÇO CADASTRADO!");
        return enderecoRepository.save(endereco);
    }
}
