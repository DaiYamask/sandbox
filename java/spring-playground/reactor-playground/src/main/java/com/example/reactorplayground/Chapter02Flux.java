package com.example.reactorplayground;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
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
                    long i = state.incrementAndGet();
                    sink.next("3 x " + state + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state -> System.out.println("state: " + state))
        );
    }

    public Flux<String> fluxWithHandle() {
        return Flux.just(-1, 20, 1, 7, 2, 1, 14, 7, 5, 18, 19)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null) {
                        sink.next(letter);
                    }
                });
    }

    public Flux<String> fluxOnErrorReturn() {
        return Flux.just(3, 1, 0)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorReturn("Divided by zero :(");
    }

    public Flux<String> fluxOnErrorReturnDefaultValue() {
        return Flux.interval(Duration.ZERO.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("Boom!!!");
                })
                .onErrorReturn("Uh oh :(");
    }

    public Flux<String> retry() {
        return Flux.interval(Duration.ZERO.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("Boom!!!");
                })
                .retry(1);
    }

    Flux<String> restoringMissingLetter(String s, List<String> words) {
        Mono<String> mono = Mono.just(s);
        return Flux.fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split(" ")))
                .concatWith(mono)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));
    }

    private String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return  "" + (char) letterIndexAscii;
    }
}
