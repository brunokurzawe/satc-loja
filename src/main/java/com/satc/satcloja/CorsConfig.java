package com.satc.satcloja;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Permitir solicitações de todos os origens
        configuration.addAllowedMethod("*"); // Permitir todos os métodos (GET, POST, etc.)
        configuration.addAllowedHeader("*"); // Permitir todos os cabeçalhos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      //  source.registerCorsConfiguration("/graphql/**", configuration); // Aplicar apenas ao endpoint GraphQL

        return source;
    }
}
