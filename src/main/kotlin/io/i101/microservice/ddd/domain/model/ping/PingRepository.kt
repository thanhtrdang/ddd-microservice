package io.i101.microservice.ddd.domain.model.ping

import io.i101.library.ddd.Repository

interface PingRepository: Repository<PingEntity, String> {
    fun findXXX()
}