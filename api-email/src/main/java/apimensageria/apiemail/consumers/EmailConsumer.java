package apimensageria.apiemail.consumers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import apimensageria.apiemail.dtos.EmailDTO;
import apimensageria.apiemail.entities.EmailEntity;
import apimensageria.apiemail.services.EmailService;

@Component
public class EmailConsumer {

  private static final Logger logger = LogManager.getLogger(EmailConsumer.class);

  @Autowired
  EmailService emailService;

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void listen(@Payload EmailDTO emailDto) {
    logger.info("INFORMAÇÕES RECEBIDAS");

    EmailEntity emailEntity = new EmailEntity();
    BeanUtils.copyProperties(emailDto, emailEntity);
    emailService.sendEmail(emailEntity);
  }
}