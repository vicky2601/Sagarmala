package org.sagarmala.service.impl;

import javax.mail.internet.MimeMessage;

import org.sagarmala.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements IEmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
	@Autowired
	private JavaMailSender emailSender;

	@Async
	public void sendEmail(MimeMessage email) {
		emailSender.send(email);
	}

	public MimeMessage sendVerificationMail(String to, String cc, String bcc, String subject, String body) {
		LOGGER.info("Executing sendVerificationMail method of EmailServiceImpl");
		MimeMessage message = null;
		MimeMessageHelper helper = null;
		try {
			message = emailSender.createMimeMessage();
			helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			if (cc != null) {
				helper.setCc(cc);
			}
			if (bcc != null) {
				helper.setBcc(bcc);
			}
			helper.setSubject(subject);
			helper.setText(body, true);

			sendEmail(message);
		} catch (Exception ex) {
			LOGGER.error("Exception occurred in sendVerificationMail method : " + ex);
		}
		LOGGER.info("sendVerificationMail method execution completed #####");
		return message;
	}
}