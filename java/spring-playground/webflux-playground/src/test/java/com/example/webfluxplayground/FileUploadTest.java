package com.example.webfluxplayground;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

/**
 * @author DAI Yamasaki
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void helloWith() {
        this.webTestClient
                .get().uri("/hello?name=<name>", "name").accept(MediaType.TEXT_PLAIN).exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello name");
    }

    @Test
    void uploadFile() {
        final MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", "");

        this.webTestClient
                .post().uri("/upload").accept(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isOk().expectBody(String.class);
    }
}