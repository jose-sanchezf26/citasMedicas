package com.formacion.citasMedicas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customSwaggerConfit(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de Citas Médicas")
                        .version("1.0.0")
                        .description("Documentación de la API REST para gestión de pacientes, médicos y diagnósticos.")
                        .contact(new Contact()
                                .name("Jose Antonio")));
    }
}
