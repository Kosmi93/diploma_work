package ru.skypro.homework.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Graduate work",
                description = "API дипломного проекта",
                version = "1.0.0",
                contact = @Contact(
                        name = "Team To Win"
                )
        )
)
public class OpenApiConfig {
}
