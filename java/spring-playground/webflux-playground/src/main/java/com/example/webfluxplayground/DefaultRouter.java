package com.example.webfluxplayground;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author DAI Yamasaki
 */
@Configuration
public class DefaultRouter {

    @Bean
    RouterFunction<ServerResponse> routes(FileUploadHandler handler) {
        return handler.routes();
    }
}
