package com.mcb.email.repositories;

import java.util.UUID;

import com.mcb.email.entities.EmailModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
