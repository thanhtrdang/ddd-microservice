package io.i101.microservice.ddd.interfaces

object Endpoint {
    const val namespace     = "/ddd/v1"

    const val ping_ping     = "/ping"
    const val ping_find     = "/find/{id}"
    const val ping_store    = "/store"
    const val ping_stream   = "/stream"
}