package com.example.webfluxplayground;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.nio.file.Paths;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author DAI Yamasaki
 */
@Component
@Log4j2
public class FileUploadHandler {

    Mono<ServerResponse> getHello(ServerRequest request) {
        return ok().contentType(MediaType.TEXT_PLAIN)
                .bodyValue("Hello Webflux");
    }

    Mono<ServerResponse> upload(ServerRequest request) {
        log.info("handling file upload func");
        final Mono<String> mono = request.body(BodyExtractors.toMultipartData()).filter(part -> part instanceof FilePart)
                .ofType(FilePart.class)
                .flatMap(this::saveFile);
        return ok().contentType(MediaType.TEXT_PLAIN).body(mono, String.class);
    }

    public RouterFunction<ServerResponse> routes() {
        return route().path("/", (builder) -> builder //
                .GET("/", this::getHello) //
                .POST("/upload", this::upload)
        ).build();
    }

    private Mono<String> saveFile(FilePart filePart) {
        log.info("handling file upload {}", filePart.filename());
        filePart.transferTo(Paths.get("/tmp/" + filePart.filename()));
        return Mono.just("OK");
    }
}
