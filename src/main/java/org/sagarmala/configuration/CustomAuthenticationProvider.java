package org.sagarmala.configuration;

import java.util.Collection;
import java.util.Map;

import org.sagarmala.model.LoginTrail;
import org.sagarmala.model.UserDetail;
import org.sagarmala.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/*
 * @author Vicky 
*/
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) {
		LoginTrail loginTrail = new LoginTrail();
		Map<String, String> map = (Map<String, String>) authentication.getDetails();
		String username = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();
		String password = null;
		Collection<? extends GrantedAuthority> authorities = null;
		UserDetails user = userDetailsService.loadUserByUsername(username.trim());
		LOGGER.info(rawPassword + " " + map);
		LOGGER.info("check...." + passwordEncoder.matches(rawPassword, user.getPassword()));
		UserDetail userDetail = userService.findByUserName(username.trim());
		loginTrail.setLoginIp(map.get("ipaddress"));
		loginTrail.setUserId(userDetail.getUserId());
		authorities = user.getAuthorities();
		System.out.println(authorities);
		if (passwordEncoder.matches(rawPassword, user.getPassword())) {
			loginTrail.setIslogIn(1);
			loginTrail.setRemarks("success");
			int loginId = userService.loginTrail(loginTrail);
			userDetail.setLoginId(loginId);
			userService.saveUser(userDetail);
			return new UsernamePasswordAuthenticationToken(user, password, authorities);
		} else {
			loginTrail.setIslogIn(0);
			loginTrail.setRemarks("failed");
			userService.loginTrail(loginTrail);
			throw new UserOauthException("Invalid username or password.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
