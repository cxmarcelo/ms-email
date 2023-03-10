package com.mcb.email.services;

import java.time.LocalDateTime;

import com.mcb.email.entities.EmailModel;
import com.mcb.email.enums.StatusEmail;
import com.mcb.email.repositories.EmailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmailService {

	@Autowired
	private EmailRepository repo;


	@Autowired
	private JavaMailSender emailSender;



	@Transactional
	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);

			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		} finally {
			emailModel = repo.save(emailModel);
		}


		return emailModel;
	}

}
