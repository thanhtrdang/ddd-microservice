package io.i101.microservice.ddd.application

import reactor.core.publisher.Mono

interface PingService {
    fun ping(): Mono<String>
}