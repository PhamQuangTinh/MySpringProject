package ou.phamquangtinh.entity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "auditorDateTimeProvider")
public class    JpaAuditingConfiguration {

    @Bean(name = "auditorProvider")
    public AuditorAware<String> auditorProvider() {

        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal() instanceof UserDetails
                ? ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername() : SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString());

    }

    @Bean(name = "auditorDateTimeProvider")
    public DateTimeProvider auditorDateTimeProvider() {
        return () -> Optional.ofNullable(LocalDateTime.now());
    }

}
