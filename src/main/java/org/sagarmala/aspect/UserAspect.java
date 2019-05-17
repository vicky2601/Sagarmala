package org.sagarmala.aspect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.internet.MimeMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.sagarmala.model.UserDetail;
import org.sagarmala.service.IEmailService;
import org.sagarmala.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAspect.class);

	@Autowired
	private IEmailService emailService;

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private Environment environment;

	/*
	 * used for User Creation
	 */
	@AfterReturning(value = "execution(* org.sagarmala.dao.impl.UserDaoImpl.saveUserDetails(..))")
	public void saveUserDetail(JoinPoint joinPoint) {
		Object[] methodArguments = joinPoint.getArgs();
		for (int i = 0; i < methodArguments.length; i++) {
			if (methodArguments[i] instanceof UserDetail) {
				UserDetail userBean = (UserDetail) methodArguments[i];
				LOGGER.info(userBean.toString());

			}
		}
	}

	public void sendEmailAndSms(UserDetail userDetail) {
		ExecutorService executorService = null;
		try {
			Thread smsThread = new Thread(() -> {
				String smsString = "Your User " + userDetail.getUserName() + " and Password "
						+ environment.getProperty("user.default.password");
				smsService.sendOTP(userDetail.getMobileNumber(), smsString);
				LOGGER.info("Sms Send Successfully ############### " + userDetail.getMobileNumber());
			});
			Thread emailThread = new Thread(() -> {
				String emailString = "Your User " + userDetail.getUserName() + " and Password "
						+ environment.getProperty("user.default.password");
				String emailId = userDetail.getEmail();
				if (emailId != null && !emailId.isEmpty()) {
					String to = emailId;
					String cc = null;
					String bcc = null;
					String subject = "SagarMala User";
					MimeMessage mailMessage = emailService.sendVerificationMail(to, cc, bcc, subject, emailString);
					emailService.sendEmail(mailMessage);
					LOGGER.info("Email Send Successfully ############### " + emailId);
				}
			});
			executorService = Executors.newFixedThreadPool(2);
			executorService.execute(smsThread);
			executorService.execute(emailThread);

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		} finally {
			if (executorService != null) {
				executorService.shutdown();
			}
		}
	}
}
