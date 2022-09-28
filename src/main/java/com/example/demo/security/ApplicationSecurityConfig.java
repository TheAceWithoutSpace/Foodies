package com.example.demo.security;

import com.example.demo.api.user.UserServiceImp;
import com.example.demo.security.jwt.JwtConfig;
import com.example.demo.security.jwt.JwtTokenVerifier;
import com.example.demo.security.jwt.JwtUserNameAndPasswordAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceImp userServiceImp;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUserNameAndPasswordAuthFilter(authenticationManager(),jwtConfig,secretKey)) //setting the Jwt Filter
                .addFilterAfter(new JwtTokenVerifier(secretKey,jwtConfig), JwtUserNameAndPasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/api/login/**").permitAll()
                .anyRequest()
                .authenticated();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userServiceImp);
        return provider;
    }
}
