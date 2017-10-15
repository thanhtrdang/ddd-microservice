package io.i101.microservice.ddd.application.impl

import io.i101.microservice.ddd.application.PingService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PingServiceImpl: PingService {
    override fun ping(): Mono<String> {
        return Mono.just("PONG")
    }
}