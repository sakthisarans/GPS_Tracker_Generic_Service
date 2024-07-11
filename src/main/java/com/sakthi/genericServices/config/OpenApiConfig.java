package com.sakthi.genericServices.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${tracker.gateway.url}")
    String gatewayuri;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(gatewayuri);
        devServer.setDescription("Auth Service");
        return new OpenAPI().servers(List.of(devServer));
    }
}

