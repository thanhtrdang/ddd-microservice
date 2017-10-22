package io.i101.microservice.ddd.domain.model.ping

import io.i101.library.ddd.ValueObject
import java.util.regex.Pattern
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PingValueObject(@Column(name = "email") val value: String): ValueObject<PingValueObject> {
    companion object {
        private val PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})\$")
    }

    val localPart: String get() = part(0)
    val domain: String get() = part(1)

    private fun part(index: Int) = value.split("@")[index]
}