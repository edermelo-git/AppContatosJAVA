package br.com.etm.AppListContatos.config;

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
                        .title("API de Controle")
                        .description("Este projeto apresenta uma sofisticada API REST construída em Java utilizando o Spring Boot. Sua finalidade é o gerenciamento eficiente de um sistema de cadastro para Pessoas e seus respectivos Contatos. A API oferece suporte completo para operações CRUD (Criar, Ler, Atualizar e Deletar), permitindo uma gestão abrangente e dinâmica tanto das Pessoas quanto dos seus Contatos associados.")
                        .contact(new Contact().name("Nome").email("Email").url("URL"))
                        .version("Versão API"));
    }
}