package io.i101.microservice.ddd.interfaces

import io.i101.library.ddd.IDGenerator
import io.i101.microservice.ddd.application.PingService
import io.i101.microservice.ddd.domain.model.ping.PingEntity
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import io.i101.microservice.ddd.domain.model.ping.PingValueObject
import io.i101.microservice.ddd.interfaces.Endpoint.namespace
import io.i101.microservice.ddd.interfaces.Endpoint.ping_find
import io.i101.microservice.ddd.interfaces.Endpoint.ping_store
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping(namespace)
class PingController(val pingRepository: PingRepository, val pingService: PingService) {
    @GetMapping(Endpoint.ping_ping)
    fun ping(): Mono<String> {
        return pingService.ping()
    }

    @GetMapping(ping_find)
    fun find(@PathVariable id: String): Mono<PingEntity> {
        return pingRepository.find(id)
    }

    @PostMapping(ping_store)
    fun store(): Mono<PingEntity> {
        val age = Random().nextInt(100)
        val pingValueObject = PingValueObject("thanhtrdang$age@gmail.com")
        val pingEntity = PingEntity(name = IDGenerator.next, age = age, email = pingValueObject)

        return pingRepository.store(pingEntity)
    }
}