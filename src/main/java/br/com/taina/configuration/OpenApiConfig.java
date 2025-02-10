package br.com.taina.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
    public OpenAPI customOpenAPI() {        
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("Controle de contatos")
                        .description("Essa api é para fazer um controle de dados ... ")
                        .contact(new Contact().name("Taina").email("tainassilva").url("https://mail.google.com/mail/u/0/#inbox?compose=new"))
                        .version("Versão API"));
    }
}
