package org.sagarmala.configuration;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.sagarmala.dao.UserDao;
import org.sagarmala.model.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/*
 * @author Vicky 
*/
public class CustomTokenEnhancer implements TokenEnhancer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomTokenEnhancer.class);

	@Autowired
	private UserDao userDao;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (authentication.getPrincipal() instanceof User) {
			User user = (User) authentication.getPrincipal();
			final Map<String, Object> additionalInfo = new HashMap<>();
			UserDetail userDetail = userDao.findByUserName(user.getUsername());
			additionalInfo.put("isFirstAttempt", userDetail.getIsFirstAttempt());
			additionalInfo.put("level", userDetail.getOrganisationId());
			additionalInfo.put("login", userDetail.getLoginId());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;
		} else {
			LOGGER.info(accessToken.getAdditionalInformation() + "Access token :- " + accessToken.toString());
			Jwt jwt = JwtHelper.decode(accessToken.toString());
			JSONObject jsonObject = new JSONObject(jwt.getClaims());			
			final Map<String, Object> additionalInfo = new HashMap<>();
			UserDetail userDetail = userDao.findByUserName(jsonObject.getString("user_name"));
			additionalInfo.put("isFirstAttempt", userDetail.getIsFirstAttempt());
			additionalInfo.put("level", userDetail.getOrganisationId());
			additionalInfo.put("login", userDetail.getLoginId());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;			
		}
	}
}
