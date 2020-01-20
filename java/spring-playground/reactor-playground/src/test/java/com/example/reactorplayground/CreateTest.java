package com.example.reactorplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author DAI Yamasaki
 */
class CreateTest {

    Chapter01Mono chapter01Mono = new Chapter01Mono();

    @Test
    void emptyMono() {
        final Mono<String> mono = this.chapter01Mono.emptyMono();
        StepVerifier.create(mono)
                .verifyComplete();
    }

    @Test
    void emptyMonoUseJust() {
        final Mono<String> mono = this.chapter01Mono.emptyMonoUseJust();
        StepVerifier.create(mono)
                .verifyComplete();
    }

    @Test
    void monoUseCreate() {
        // todo
    }

    @Test
    void error() {
        final Mono<String> mono = this.chapter01Mono.errorMono();
        StepVerifier.create(mono)
                .expectError(IllegalStateException.class);
    }
}