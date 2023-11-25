package apimensageria.apiemail.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import apimensageria.apiemail.entities.EmailEntity;
import apimensageria.apiemail.entities.enums.StatusSend;
import apimensageria.apiemail.repositories.EmailRepository;

@Service
public class EmailService {

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

      emailEntity.setStatusSend(StatusSend.SENT);
    } catch (MailException e) {
      emailEntity.setStatusSend(StatusSend.ERROR);
    } finally {
      this.emailRepository.save(emailEntity);
    }

    return emailEntity;
  }
}