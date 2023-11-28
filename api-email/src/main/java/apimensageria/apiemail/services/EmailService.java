package apimensageria.apiemail.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import apimensageria.apiemail.controllers.EmailController;
import apimensageria.apiemail.entities.EmailEntity;
import apimensageria.apiemail.entities.enums.StatusSend;
import apimensageria.apiemail.repositories.EmailRepository;

@Service
public class EmailService {
  private static final Logger logger = LogManager.getLogger(EmailService.class);

  @Value("${spring.mail.username}")
  private String emailEnterprise;

  private EmailRepository emailRepository;

  private JavaMailSender emailSender;

  public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
    this.emailRepository = emailRepository;
    this.emailSender = emailSender;
  }

  public EmailEntity sendEmail(EmailEntity emailEntity) {
    emailEntity.setSendDateEmail(LocalDateTime.now());
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(emailEnterprise);

      List<String> toList = new ArrayList<>();
      toList.add(emailEntity.getEmailPessoa());
      toList.add(emailEntity.getEmailEmpresa());

      for (String recipient : toList) {
        message.setTo(recipient);
        message.setSubject(emailEntity.getAssunto());
        message.setText(emailEntity.getMensagem());
        emailSender.send(message);
      }

      logger.info("EMAIL ENVIADO COM SUCESSO!");
      emailEntity.setStatusSend(StatusSend.SENT);
    } catch (MailException e) {
      logger.error("ERRO NO ENVIO DO EMAIL: " + e);
      emailEntity.setStatusSend(StatusSend.ERROR);
    } finally {
      this.emailRepository.save(emailEntity);
      logger.info("EMAIL SALVO NO BANCO DE DADOS!");
    }

    return emailEntity;
  }
}