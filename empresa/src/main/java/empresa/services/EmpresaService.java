package empresa.services;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import empresa.entities.Empresa;
import empresa.repositories.EmpresaRepository;
import empresa.services.exceptions.DataIntegratyViolationException;

@Service
public class EmpresaService {

    private static final Logger logger = LogManager.getLogger(EmpresaService.class);

    @Value("${brasilapi.cnpj.url}")
    private String brasilApiCnpjUrl;

    private EmpresaRepository empresaRepository;

    private final RestTemplate restTemplate;

    public EmpresaService(EmpresaRepository empresaRepository, RestTemplate restTemplate) {
        this.empresaRepository = empresaRepository;
        this.restTemplate = restTemplate;
    }

    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    public boolean validarCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");

        String apiUrl = brasilApiCnpjUrl + cnpj;
        try {
            Map<String, Object> responseData = restTemplate.getForObject(apiUrl, Map.class);

            if (responseData != null && responseData.containsKey("message")) {
                logger.error("O CNPJ É INVALIDO!");
                return false;
            }
        } catch (Exception e) {
            logger.error("OCORREU ERRO NA REQUISIÇÃO API DO CNPJ");
            return false;
        }
        return true;
    }

    public List<Empresa> cadastrarEmpresa(Empresa empresa) {
        if (!validarCnpj(empresa.getCnpj())) {
            logger.error("Esse CNPJ é inválido, tente outro!");
            throw new DataIntegratyViolationException("Esse CNPJ é inválido, tente outro!");
        }

        if (empresaRepository.existsByCnpj(empresa.getCnpj())) {
            logger.error("Esse CNPJ já está cadastrado, tente outro!");
            throw new DataIntegratyViolationException("Esse CNPJ já está cadastrado, tente outro!");
        }

        if (empresaRepository.existsByEmail(empresa.getEmail())) {
            logger.error("Esse E-mail já está cadastrado, tente outro!");
            throw new DataIntegratyViolationException("Esse E-mail já está cadastrado, tente outro!");
        }

        empresaRepository.save(empresa);
        logger.info("EMPRESA CADASTRADA!");
        return this.getAllEmpresas();
    }
}
