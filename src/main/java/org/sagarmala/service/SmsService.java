package org.sagarmala.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Author : Vicky This class is used to Send the generate username and password
 * for particular Mobile NUmber
 */
@Service
public class SmsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

	@Autowired
	private Environment environment;

	/**
	 * Send Message (UserName And Password)
	 * 
	 * @param mobileNumber
	 * @param message
	 */
	public String sendOTP(String mobileNumber, String message) {
		String userName = environment.getProperty("sms.username");
		String pin = environment.getProperty("sms.pin");
		String senderID = environment.getProperty("sms.senderid");
		String output;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		HttpsURLConnection uc = null;
		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, // key manager
					trust_mgr, // trust manager
					new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			String requestUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username="
					+ URLEncoder.encode(userName, "UTF-8") + "&pin=" + pin + "&message="
					+ URLEncoder.encode(message, "UTF-8") + "&mnumber=" + "91" + mobileNumber + "&signature="
					+ senderID;
			LOGGER.info("requestUrl===>" + requestUrl);
			LOGGER.info("OTPSend:: Username: " + userName + " pin: " + pin + " message: " + message + " senderId: "
					+ senderID + " requestUrl: " + requestUrl);
			URL url = new URL(requestUrl);
			uc = (HttpsURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader((uc.getInputStream())));
			while ((output = br.readLine()) != null) {
				sb.append(output);
				LOGGER.info("Message Received : " + sb.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			sb.append(ex.getMessage());
			LOGGER.error("Error while send the SMS" + ex.getMessage());
		} finally {
			try {
				br.close();
				uc.disconnect();
			} catch (Exception ex) {
				LOGGER.error("Exception occurred in closing object in sendOTP method :" + ex);
			}
		}
		return sb.toString();
	}

	private TrustManager[] get_trust_mgr() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String t) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String t) {
			}
		} };
		return certs;
	}
}