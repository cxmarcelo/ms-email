package com.mcb.email.controllers;

import com.mcb.email.dtos.EmailDto;
import com.mcb.email.entities.EmailModel;
import com.mcb.email.services.EmailService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class EmailController {

	@Autowired
	private EmailService service;

	Logger logger = LogManager.getLogger(EmailController.class);
	
	@PostMapping
	public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
		logger.trace(""); //muitos detalhes
		logger.debug(""); //dev
		logger.info(""); //prod do que esta ocorrendo
		logger.warn(""); // alerta 
		logger.error(""); //msg de erro algo inesperado try catch
		logger.fatal(""); //erro critico quando uma funcionalidade para de funcionar
		
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		service.sendEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
	}
}
