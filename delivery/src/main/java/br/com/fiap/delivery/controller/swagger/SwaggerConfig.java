package br.com.fiap.delivery.controller.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersApi() {

        Info info = new Info()
                .title("Api para gerenciamento de entregas")
                .version("1.0.0")
                .description("Esta API expõe endpoints para gerenciar entrega de pedidos");

        List<Tag> tags = Arrays.asList(
                new Tag().name("Entregas").description("Gerenciamento de entregas")
        );

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8084");
        localServer.setDescription("Server URL local");

        return new OpenAPI().info(info).tags(tags).servers(Arrays.asList(localServer));

    }

}
