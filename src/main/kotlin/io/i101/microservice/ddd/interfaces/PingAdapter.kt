package io.i101.microservice.ddd.interfaces

import io.i101.microservice.ddd.domain.model.ping.PingEntity

class PingAdapter(private val ping: PingEntity) {
    val id: String get() = ping.id
    val email: String get() = ping.email.value
    val age: Int get() = ping.age
}