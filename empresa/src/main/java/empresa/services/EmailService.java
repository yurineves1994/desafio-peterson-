package empresa.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import empresa.dtos.EmailDTO;

@Service
public class EmailService {

    @Value("${mensageria.url}")
    private String apiUrlMensageria;

    private final RestTemplate restTemplate;

    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarApiMensageria(String emailEmpresa, String emailPessoa, String assunto, String mensagem, String nomePessoa) {

        EmailDTO email = new EmailDTO(emailEmpresa, emailPessoa, assunto, mensagem, nomePessoa);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EmailDTO> requestEntity = new HttpEntity<>(email, headers);

        restTemplate.postForEntity(apiUrlMensageria, requestEntity, Void.class);

    }
}
