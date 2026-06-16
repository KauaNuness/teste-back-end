package com.kaua.testebackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Teste Backend API",
                version = "1.0.0",
                description = "API para gerenciamento de usuários e endereços com validação de CEP via ViaCEP."
        )
)
public class OpenApiConfig {
}