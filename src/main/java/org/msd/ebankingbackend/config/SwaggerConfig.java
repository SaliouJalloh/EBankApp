package org.msd.ebankingbackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${application.backend.basePath}")
    private String backendUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer")
                .addList(String.valueOf(new Scopes().addString("global", "access all APIs")));
        return new OpenAPI(SpecVersion.V30).info(apiInfo()).externalDocs(new ExternalDocumentation()).servers(servers())
                .security(Collections.singletonList(securityRequirement))
                .components(new Components().addSecuritySchemes("Bearer",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    private Info apiInfo() {
        Info info = new Info();
        info.title("Swagger Documentation");
        info.description("Swagger pour backend ebank: Gestion des utilisateurs");
        return info;
    }

    private List<Server> servers() {
        Server server = new Server();
        server.url(backendUrl);
        return List.of(server);
    }
}
