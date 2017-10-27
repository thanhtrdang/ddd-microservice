package io.i101.microservice.ddd.interfaces

import io.i101.microservice.ddd.ProtobufEncoder
import io.i101.microservice.ddd.domain.model.ping.PingEntity

class PingAdapter(private val ping: PingEntity) {
    val id: String get() = ping.id
    val email: String get() = ping.email.value
    val age: Int get() = ping.age
}

class PingEncoder(private val entity: PingEntity): ProtobufEncoder<PingEntity, PingMessage>(entity) {
    override fun encode(entity: PingEntity): PingMessage = PingMessage.Builder()
            .id(entity.id)
            .name(entity.name)
            .email(entity.email.value)
            .age(entity.age)
            .build()
}