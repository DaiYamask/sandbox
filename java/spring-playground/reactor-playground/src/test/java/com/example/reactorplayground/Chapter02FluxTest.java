package com.example.reactorplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author DAI Yamasaki
 */
public class Chapter02FluxTest {

    @Test
    void createWithJust() {
        final Flux<Integer> flux = Flux.just(1);
        StepVerifier.create(flux)
                .expectNext(1)
                .verifyComplete();
    }

    @Test
    void createWithJustAndRepeat() {
        final Flux<Integer> flux = Flux.just(1).repeat();
        StepVerifier.create(flux)
                .expectNext(1, 1, 1, 1, 1, 1, 1);

    }
    @Test
    void expectError() {
        final Flux<Object> flux = Flux.error(NullPointerException::new);
        StepVerifier.create(flux)
                .expectError(NullPointerException.class)
                .verify();
    }
}
