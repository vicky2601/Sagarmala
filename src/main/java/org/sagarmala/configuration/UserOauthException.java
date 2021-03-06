package org.sagarmala.configuration;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/*
 * @author Vicky 
*/
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class UserOauthException extends OAuth2Exception {

	private static final long serialVersionUID = 1L;

	public UserOauthException(String msg) {
		super(msg);
	}
}
