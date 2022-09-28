package com.example.demo.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.net.HttpHeaders;

@Configuration
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private String fromEmail;
    private int tokenExpirationAfterDays;
    private String emailVerificationUrl;
    public String getAuthorizationHeader(){
            return HttpHeaders.AUTHORIZATION;
    }


}
