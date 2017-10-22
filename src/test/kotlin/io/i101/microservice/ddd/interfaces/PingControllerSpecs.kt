package io.i101.microservice.ddd.interfaces

import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.i101.microservice.ddd.application.PingService
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import reactor.core.publisher.Mono
import reactor.test.test

object PingControllerSpecs: Spek({
    describe("ping") {
        val pingResult = "PONG PONG PONG"
        val pingServiceMock = mock<PingService> {
            on { ping() } doReturn Mono.just(pingResult)
        }
        val pingRepositoryMock = mock<PingRepository> {
            on { findEntity() } doAnswer { null }
            on { findValueObject() } doAnswer { null }
        }

        val pingController = PingController(pingRepositoryMock, pingServiceMock)

        it("should return $pingResult") {
            val pongMono = pingController.ping()

            pongMono.test()
                    .expectNext(pingResult)
                    .verifyComplete()

            verify(pingServiceMock).ping()
        }
    }
})