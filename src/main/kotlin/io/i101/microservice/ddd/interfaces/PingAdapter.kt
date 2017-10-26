package io.i101.microservice.ddd.interfaces

import io.i101.microservice.ddd.domain.model.ping.PingEntity
import kotlinx.serialization.SerialId
import kotlinx.serialization.Serializable

@Serializable
class PingAdapter(private val ping: PingEntity) {
    @SerialId(1) val id: String get() = ping.id
    @SerialId(2) val email: String get() = ping.email.value
    @SerialId(3) val age: Int get() = ping.age
}