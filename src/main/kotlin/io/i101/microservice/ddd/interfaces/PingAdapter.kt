package io.i101.microservice.ddd.interfaces

import io.i101.microservice.ddd.domain.model.ping.PingEntity

data class PingAdapter(private val ping: PingEntity) {
    val id: String get() = ping.id
    val name: String get() = ping.name
    val email: String get() = ping.email.value
    val age: Int get() = ping.age
}