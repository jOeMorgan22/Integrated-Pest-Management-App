package com.ipm_manager_ws.ipm_manager_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ipm_manager_ws.ipm_manager_app.filter.JWTValidationFilter;
import com.ipm_manager_ws.ipm_manager_app.filter.JWTGenerationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final RSAKeyProperties rsaKeyProperties;

    private CustomAccessDeniedHandler customAccessDeniedHandler;

    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    
    public SecurityConfig(RSAKeyProperties rsaKeyProperties, CustomAccessDeniedHandler customAccessDeniedHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.rsaKeyProperties = rsaKeyProperties;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JWTValidationFilter(rsaKeyProperties), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTGenerationFilter(rsaKeyProperties), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.POST, "/register", "/register-with-group-code", "verify/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/users/login")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/reports/users/{userId}", "/reports/{reportId}", "/actions/{actionId}", "/actions/reports/{reportId}")
                                .hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/group", "/reports/group-reports")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/reports/users/{userId}", "/actions/reports/{reportId}", "/join-group")
                                .hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/reports/{reportId}", "/create-group")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/reports/{reportId}", "/actions/{actionId}")
                                .hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users")
                                .hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/reports/{reportId}", "/actions/{actionId}")
                                .hasRole("ADMIN")
                )
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler(customAccessDeniedHandler))
                .formLogin(login -> login
                /*placeholder @GetMapping with no templates
                        .failureUrl("/login-error"))*/
                        .failureHandler(customAuthenticationFailureHandler))
                .httpBasic(withDefaults());
                
        return http.build();

    }

    @Bean 
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
