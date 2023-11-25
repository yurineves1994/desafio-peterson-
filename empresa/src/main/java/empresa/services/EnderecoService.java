package empresa.services;

import java.util.List;
import java.util.Map;

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
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao validar CEP: " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco cadastrarEndereco(Long idEmpresa, Endereco endereco) {

        if (!validarCep(endereco.getCep())) {
            throw new DataIntegratyViolationException("CEP inválido");
        }

        if (enderecoRepository.existsByCep(endereco.getCep())) {
            throw new DataIntegratyViolationException("Esse CEP já está cadastrado!");
        }

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new DataIntegratyViolationException("Empresa não foi Localizada!"));

        endereco.setEmpresa(empresa);

        return enderecoRepository.save(endereco);
    }
}
