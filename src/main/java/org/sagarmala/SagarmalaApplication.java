package org.sagarmala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootConfiguration
@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class })
@EnableAspectJAutoProxy
public class SagarmalaApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SagarmalaApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SagarmalaApplication.class, args);
	}
}