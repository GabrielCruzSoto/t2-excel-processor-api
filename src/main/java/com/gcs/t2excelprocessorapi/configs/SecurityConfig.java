package com.gcs.t2excelprocessorapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/v3/api-docs/**", "/swagger-ui/*").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");  // Permitir solo tu frontend
        configuration.addAllowedMethod(HttpMethod.GET);  // Permitir GET
        configuration.addAllowedMethod(HttpMethod.POST);  // Permitir POST
        configuration.addAllowedMethod(HttpMethod.PUT);  // Permitir PUT
        configuration.addAllowedMethod(HttpMethod.DELETE);  // Permitir DELETE
        configuration.addAllowedMethod(HttpMethod.OPTIONS);  // Permitir OPTIONS (para solicitudes preflight)
        configuration.addAllowedHeader("*");  // Permitir el encabezado Content-Type
        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("X-Requested-With");
        configuration.setAllowCredentials(true);  // Permitir el envío de cookies o credenciales
        configuration.setMaxAge(3600L);  // Tiempo de cacheo de la respuesta preflight

        // Devolver la configuración
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
