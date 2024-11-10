package com.qp.assessment.gsms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("app")
public class AppProperties {

	private String jwtSecret;
	
}
