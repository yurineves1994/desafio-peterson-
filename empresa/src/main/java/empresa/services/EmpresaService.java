package empresa.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import empresa.entities.Empresa;
import empresa.repositories.EmpresaRepository;
import empresa.services.exceptions.DataIntegratyViolationException;

@Service
public class EmpresaService {

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
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao validar CNPJ: " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Empresa> cadastrarEmpresa(Empresa empresa) {
        if (!validarCnpj(empresa.getCnpj())) {
            throw new DataIntegratyViolationException("Esse CNPJ é inválido, tente outro!");
        }

        if (empresaRepository.existsByCnpj(empresa.getCnpj())) {
            throw new DataIntegratyViolationException("Esse CNPJ já está cadastrado, tente outro!");
        }

        if (empresaRepository.existsByEmail(empresa.getEmail())) {
            throw new DataIntegratyViolationException("Esse E-mail já está cadastrado, tente outro!");
        }

        empresaRepository.save(empresa);

        return this.getAllEmpresas();
    }
}
