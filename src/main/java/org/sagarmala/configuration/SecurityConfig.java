package org.sagarmala.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/*
 * @author Vicky 
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.signing-key}")
	private String signingKey;

	@Value("${security.encoding-strength}")
	private Integer encodingStrength;

	@Value("${security.security-realm}")
	private String securityRealm;

	@Value("${security.jwt.token-validity}")
	private int tokenValidity;

	@Value("${security.jwt.refresh-token-validity}")
	private int refreshtokenValidity;

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/login", "/oauth/authorize").and().authorizeRequests().anyRequest()
				.authenticated().and().formLogin().permitAll().and().exceptionHandling()
				.accessDeniedPage("/my-error-page").and().headers().frameOptions().sameOrigin();
		/*
		 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
		 * STATELESS).and().httpBasic()
		 * .realmName(securityRealm).and().exceptionHandling().accessDeniedPage(
		 * "/accessDenied") .and().csrf().disable();
		 */
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		/*
		 * JwtTokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
		 * tokenStore.setApprovalStore(new InMemoryApprovalStore());
		 */
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter(), tokenEnhancer()));
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setTokenEnhancer(enhancerChain);
		defaultTokenServices.setAccessTokenValiditySeconds(tokenValidity);
		defaultTokenServices.setRefreshTokenValiditySeconds(refreshtokenValidity);
		return defaultTokenServices;
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/favicon.ico").antMatchers("/")
				.antMatchers("/resources/**").antMatchers("/sagarmala/sagarmala-file/**").antMatchers("/sagarmala-file/**").antMatchers("/api/user/resetPassword")
				.antMatchers("/user/verifyToken").antMatchers("/user/forgetPassword")
				.antMatchers("/api/auth/user/logout").antMatchers("/api/auth/user/forgetPassword")
				.antMatchers("/forget/**", "/user/sendUserNameAtForgetLoginId", "/blank-list")
				.antMatchers("/v2/api-docs").antMatchers("/swagger-ui.html","/ /**","/swagger-resources/**").antMatchers("/reset-password/**").antMatchers("/api/user/forgetPassword").antMatchers("/reset/user/password/**");
	}
}
//