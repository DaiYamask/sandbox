package com.example.reactorplayground;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author DAI Yamasaki
 */
public class Chapter02Flux {

    public Flux<Integer> subscribeAndThrowException() {
        return Flux.range(1, 4)
                .map(integer -> {
                    if (integer <= 3) return integer;
                    throw new RuntimeException("Got to 4");
                });
    }

    public Flux<String> generateFlux() {
        return Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                }
        );
    }

    public Flux<String> generateFluxWithMutable() {
        return Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + state + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state -> System.out.println("state: " + state))
        );
    }
}
