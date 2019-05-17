package org.sagarmala.service;

import javax.mail.internet.MimeMessage;

public interface IEmailService {
	public void sendEmail(MimeMessage email);

	public MimeMessage sendVerificationMail(String to, String cc, String bcc, String subject, String body);
}
