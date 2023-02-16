package com.mcb.email.consumers;

import com.mcb.email.dtos.EmailDto;
import com.mcb.email.entities.EmailModel;
import com.mcb.email.services.EmailService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

	@Autowired
	private EmailService emailService;
	
	Logger logger = LogManager.getLogger(EmailConsumer.class);

	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void emailListen(@Payload EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
		System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
	}

}
