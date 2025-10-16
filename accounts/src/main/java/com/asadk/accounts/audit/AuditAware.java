package com.asadk.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAware")
public class AuditAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
	
		return Optional.of("ACCOUNTS_MS");
	}

	
	
}
