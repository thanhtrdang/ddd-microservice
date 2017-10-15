package io.i101.microservice.ddd.interfaces

import io.i101.library.ddd.IDGenerator
import io.i101.microservice.ddd.application.PingService
import io.i101.microservice.ddd.domain.model.ping.PingEntity
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class PingController(val pingRepository: PingRepository, val pingService: PingService) {

    @GetMapping("/ping")
    fun ping(): Mono<String> {
        return pingService.ping()
    }

    @GetMapping("/find/{id}")
    fun find(@PathVariable id: String): Mono<PingEntity> {
        return pingRepository.find(id)
    }

    @PostMapping("/store")
    fun store(): Mono<PingEntity> {
        val pingEntity = PingEntity(name = IDGenerator.next, age = Random().nextInt(100))
        return pingRepository.store(pingEntity)
    }
}