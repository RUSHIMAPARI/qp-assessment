package com.qp.assessment.gsms;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.qp.assessment.gsms.config.AuditorAwareImpl;
import com.qp.assessment.gsms.config.AuthorizationFilter;

@Order(0)
@ComponentScan(basePackages = "com.qp.assessment")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
@SuppressWarnings("deprecation")
public class BaseConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthorizationFilter authorizationFilter;

	@Autowired
	private AuditorAwareImpl auditAwareImpl;

	@Bean
	public AuditorAware<Long> auditorAware(){
		return auditAwareImpl;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.csrf()
		.disable()
		.headers()
		.frameOptions()
		.deny()
		.and()
		.addFilter(authorizationFilter)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}