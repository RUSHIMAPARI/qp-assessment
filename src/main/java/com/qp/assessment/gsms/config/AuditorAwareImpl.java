package com.qp.assessment.gsms.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		try {
			String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(userId != null && userId.equals("anonymousUser")) {
				return Optional.of(0l);
			}
			else {
				return Optional.of(Long.parseLong(userId));
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return Optional.of(0l);
	}

}