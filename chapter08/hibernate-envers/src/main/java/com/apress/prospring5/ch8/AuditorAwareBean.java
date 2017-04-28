package com.apress.prospring5.ch8;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareBean implements AuditorAware<String> {
    public Optional<String> getCurrentAuditor() {
        return Optional.of("prospring5");
    }
}
