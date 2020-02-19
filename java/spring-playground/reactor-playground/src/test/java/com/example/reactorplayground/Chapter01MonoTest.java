package com.example.reactorplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author DAI Yamasaki
 */
class Chapter01MonoTest {

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

    @Test
    void useLog() {
        final Mono<String> mono = this.chapter01Mono.useLog();
        StepVerifier.create(mono)
                .expectNext("use log")
                .verifyComplete();
    }

    @Test
    void useLogWithCategory() {
        final Mono<String> mono = this.chapter01Mono.useLog("category.");
        StepVerifier.create(mono)
                .expectNext("use log with category")
                .verifyComplete();

    }

    @Test
    void subscribeTwice() {
        final Mono<Integer> mono = Mono.just(1);
        mono.subscribe();
        mono.subscribe();

        StepVerifier.create(mono)
            .expectNext(1).verifyComplete();
    }

    @Test
    void fromRunnableNormal() {
        AtomicReference<Thread> t = new AtomicReference<>();
        StepVerifier.create(Mono.fromRunnable(() -> t.set(Thread.currentThread())).log()
                .subscribeOn(Schedulers.single()))
                .verifyComplete();

        assertThat(t).isNotNull();
        assertThat(t).isNotEqualTo(Thread.currentThread());
    }
}
