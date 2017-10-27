package io.i101.microservice.ddd.interfaces

import io.i101.library.ddd.IDGenerator
import io.i101.microservice.ddd.application.PingService
import io.i101.microservice.ddd.domain.model.ping.PingEntity
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import io.i101.microservice.ddd.domain.model.ping.PingValueObject
import io.i101.microservice.ddd.interfaces.Endpoint.namespace
import io.i101.microservice.ddd.interfaces.Endpoint.ping_find
import io.i101.microservice.ddd.interfaces.Endpoint.ping_ping
import io.i101.microservice.ddd.interfaces.Endpoint.ping_store
import io.i101.microservice.ddd.interfaces.Endpoint.ping_stream
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just
import java.util.*

@RestController
@RequestMapping(namespace)
class PingController(val pingRepository: PingRepository, val pingService: PingService) {
    @GetMapping(ping_ping)
    fun ping(): Mono<String> {
        return pingService.ping()
    }

    @GetMapping(ping_find)
    fun find(@PathVariable id: String): Mono<ResponseEntity<PingAdapter>> {
        return pingRepository
                .find(id)
                .map { ok(PingAdapter(it)) }
                .switchIfEmpty(just(noContent().build()))
                .onErrorReturn(status(INTERNAL_SERVER_ERROR).build())
    }

    @PostMapping(ping_store)
    fun store(): Mono<ResponseEntity<PingAdapter>> {
        val age = Random().nextInt(100)
        val pingValueObject = PingValueObject("thanhtrdang${age + 1}@gmail.com")
        val pingEntity = PingEntity(name = IDGenerator.next, age = age, email = pingValueObject)

        return pingRepository
                .store(pingEntity)
                .map { ok(PingAdapter(it)) }
                .switchIfEmpty(just(noContent().build()))
                .onErrorReturn(status(INTERNAL_SERVER_ERROR).build())
    }

    @GetMapping(ping_stream, produces = [APPLICATION_STREAM_JSON_VALUE])
    fun stream(): ResponseEntity<Flux<PingAdapter>> {
        return ok(pingRepository
                .findAllEntity()
                .map { PingAdapter(it) })
    }
}