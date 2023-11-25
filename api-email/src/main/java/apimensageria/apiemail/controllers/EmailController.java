package apimensageria.apiemail.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apimensageria.apiemail.dtos.EmailDTO;
import apimensageria.apiemail.entities.EmailEntity;
import apimensageria.apiemail.services.EmailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ms")
public class EmailController {

  private EmailService emailService;

  @PostMapping
  public ResponseEntity<EmailEntity> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
    EmailEntity emailEntity = new EmailEntity();
    BeanUtils.copyProperties(emailDTO, emailEntity);
    emailService.sendEmail(emailEntity);

    return ResponseEntity.status(HttpStatus.CREATED).body(emailEntity);
  }
}
