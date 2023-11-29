package empresa.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import empresa.dtos.EmailDTO;

@Service
public class EmailService {
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${mensageria.url}")
    private String apiUrlMensageria;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public EmailService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarApiMensageria(String emailEmpresa, String emailPessoa, String assunto, String mensagem,
            String nomePessoa) {
        EmailDTO email = new EmailDTO(emailEmpresa, emailPessoa, assunto, mensagem, nomePessoa);

        rabbitTemplate.convertAndSend(queue, email);

        logger.info("DADOS DA PERGUNTA ENVIADOS PARA API DE MENSAGERIA");
    }
}
