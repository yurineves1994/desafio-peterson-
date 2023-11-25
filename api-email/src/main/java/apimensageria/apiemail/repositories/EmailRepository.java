package apimensageria.apiemail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import apimensageria.apiemail.entities.EmailEntity;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
}