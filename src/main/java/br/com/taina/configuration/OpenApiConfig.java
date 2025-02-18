package br.com.taina.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Classe de configuração do OpenAPI (Swagger) para documentação da API.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura o OpenAPI para documentar a API RESTful.
     * 
     * @return um objeto OpenAPI com informações da API e esquema de segurança.
     */
    @Bean
    public OpenAPI customOpenAPI() {        
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")))
                .info(new Info()
                        .title("Controle de Contatos")
                        .description("API RESTful para controle de contatos de uma pessoa. "
                                + "Essa API permite cadastrar, atualizar, consultar e excluir uma pessoa, "
                                + "além de poder gerenciar contatos associados.")
                        .contact(new Contact()
                                .name("Taina Santos Silva")
                                .email("tainassilva@gmail.com")
                                .url("https://github.com/tainassilva"))
                        .version("1.0"));
    }
}
