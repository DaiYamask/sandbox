package com.example.reactorplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

/**
 * @author DAI Yamasaki
 */
public class Chapter02FluxTest {

    private Chapter02Flux chapter02Flux = new Chapter02Flux();

    // Todo create method in Chapter02Flux
    @Test
    void createWithJust() {
        final Flux<Integer> flux = Flux.just(1);
        StepVerifier.create(flux)
                .expectNext(1)
                .verifyComplete();
    }

    // Todo create method in Chapter02Flux
    @Test
    void createWithJustAndRepeat() {
        final Flux<Integer> flux = Flux.just(1).repeat();
        StepVerifier.create(flux)
                .expectNext(1, 1, 1, 1, 1, 1, 1);

    }

    // Todo create method in Chapter02Flux
    @Test
    void expectError() {
        final Flux<Object> flux = Flux.error(NullPointerException::new);
        StepVerifier.create(flux)
                .expectError(NullPointerException.class)
                .verify();
    }

    // Todo create method in Chapter02Flux
    @Test
    void createWithRange() {
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
        StepVerifier.create(numbersFromFiveToSeven)
            .expectNext(5, 6, 7)
            .verifyComplete();
    }

    // Todo create method in Chapter02Flux
    @Test
    void subscribe() {
        Flux<Integer> flux = Flux.range(0, 100).log();
        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);
        StepVerifier.create(flux)
            .expectNext(1, 2, 3);
    }

    @Test
    void subscribeAndThrowException() {
        Flux<Integer> integerFlux = this.chapter02Flux.subscribeAndThrowException();
        StepVerifier.create(integerFlux)
                .expectNext(1, 2, 3)
                .expectError()
                .verify();
    }

    @Test
    void generateFlux() {
        final Flux<String> flux = this.chapter02Flux.generateFlux().log();
        StepVerifier.create(flux)
                .expectNext("3 x 0 = 0")
                .expectNext("3 x 1 = 3")
                .expectNext("3 x 2 = 6")
                .expectNext("3 x 3 = 9")
                .expectNext("3 x 4 = 12")
                .expectNext("3 x 5 = 15")
                .expectNext("3 x 6 = 18")
                .expectNext("3 x 7 = 21")
                .expectNext("3 x 8 = 24")
                .expectNext("3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .verifyComplete();
    }

    @Test
    void generateFluxWithMutable() {
        final Flux<String> flux = this.chapter02Flux.generateFluxWithMutable();
        StepVerifier.create(flux)
                .expectNext("3 x 1 = 3")
                .expectNext("3 x 2 = 6")
                .expectNext("3 x 3 = 9")
                .expectNext("3 x 4 = 12")
                .expectNext("3 x 5 = 15")
                .expectNext("3 x 6 = 18")
                .expectNext("3 x 7 = 21")
                .expectNext("3 x 8 = 24")
                .expectNext("3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .verifyComplete();
    }

    @Test
    void fluxWithHandle() {
        final Flux<String> flux = this.chapter02Flux.fluxWithHandle();
        StepVerifier.create(flux)
                .expectNext("T", "A", "G", "B", "A", "N", "G", "E", "R", "S")
                .verifyComplete();
    }

    @Test
    void onErrorReturn() {
        final Flux<String> flux = this.chapter02Flux.fluxOnErrorReturn();
        StepVerifier.create(flux)
                .expectNext("100 / 3 = 33")
                .expectNext("100 / 1 = 100")
                .expectNext("Divided by zero :(")
                .verifyComplete();
    }

    @Test
    void fluxOnErrorReturnDefaultValue() {
        final Flux<String> tick = this.chapter02Flux.fluxOnErrorReturnDefaultValue();
        StepVerifier.create(tick)
                .expectNext("tick 0")
                .expectNext("tick 1")
                .expectNext("tick 2")
                .expectNext("Uh oh :(")
                .verifyComplete();
    }

    @Test
    void retry() {
        final Flux<String> retry = this.chapter02Flux.retry();
        StepVerifier.create(retry)
                .expectNext("tick 0")
                .expectNext("tick 1")
                .expectNext("tick 2")
                .expectNext("tick 0")
                .expectNext("tick 1")
                .expectNext("tick 2")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void restoringMissingLetter() {
        final Flux<String> s = this.chapter02Flux.restoringMissingLetter("s", List.of("The previous",  "article noted the", "resemblance between"));
        s.cache().subscribe(System.out::println);
        StepVerifier.create(s)
                .expectNext("1. The")
                .expectNext("2. article")
                .expectNext("3. between")
                .expectNext("4. noted")
                .expectNext("5. previous")
                .expectNext("6. resemblance")
                .expectNext("7. s")
                .expectNext("8. the");
    }
}
