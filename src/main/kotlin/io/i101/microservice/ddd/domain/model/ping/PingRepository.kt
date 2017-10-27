package io.i101.microservice.ddd.domain.model.ping

import io.i101.library.ddd.Repository
import reactor.core.publisher.Flux

interface PingRepository: Repository<PingEntity, String> {
    fun findEntity(): PingEntity
    fun findValueObject(): PingEntity
    fun findAllEntity(): Flux<PingEntity>
}