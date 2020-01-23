package com.example.reactorplayground;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author DAI Yamasaki
 */
public class Chapter01Mono {
    Mono<String> emptyMono() {
        return Mono.empty();
    }

    Mono<String> emptyMonoUseJust() {
        return Mono.justOrEmpty(Optional.empty());
    }

    Mono<String> monoUseCreate() {
        return  Mono.empty();
    }

    Mono<String> errorMono() {
        return Mono.error(IllegalStateException::new);
    }

    Mono<String> useLog() {
        return Mono.just("use log").log();
    }

    Mono<String> useLog(String category) {
        return Mono.just("use log with category").log("category");
    }
}
