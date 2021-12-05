package com.joaoptgaino.msmailer.repositories;

import com.joaoptgaino.msmailer.models.MailerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailerRepository extends JpaRepository<MailerModel, UUID> {
}
