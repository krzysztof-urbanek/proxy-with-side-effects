package kurbanek.proxy.controller;

import kurbanek.proxy.model.Number;
import kurbanek.proxy.repository.PostgresqlNbaPlayerJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;


@RestController
public record ProxyController(
    PostgresqlNbaPlayerJpaRepository postgresqlNbaPlayerJpaRepository
) {

    @PostMapping("/proxy/add/{number1}/{number2}")
    public Mono<String> proxy(@PathVariable Integer number1, @PathVariable Integer number2) {
        var webclient = WebClient.builder()
                .build();

        return webclient.get()
                .uri(URI.create("localhost:8080/add/" + number1 + "/" + number2))
                .retrieve()
                .bodyToMono(Long.class)
                .map(sum -> new Number(null, sum))
                .flatMap(postgresqlNbaPlayerJpaRepository::save)
                .map(n -> number1 + " + " + number2 + " = " + n.value().toString());
    }

    @GetMapping("/add/{number1}/{number2}")
    public Integer add(@PathVariable Integer number1, @PathVariable Integer number2) {
        return number1 + number2;
    }

    @GetMapping("/database")
    public Flux<String> database() {
        return postgresqlNbaPlayerJpaRepository.findAll().flatMap(n ->
                Flux.just("id: " + n.ordinalId() + ", value: " + n.value() + "\n")
        );
    }
}
