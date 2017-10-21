package io.i101.microservice.ddd.infrastructure.persistence

import io.i101.microservice.ddd.domain.model.ping.PingEntity
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import io.i101.microservice.ddd.domain.model.ping.QPingEntity.pingEntity
import org.springframework.stereotype.Repository

@Repository
class PingRepositoryImpl: RepositorySupport<PingEntity, String>(PingEntity::class.java),
        PingRepository {

    override fun findXXX(): PingEntity = with(pingEntity) {
        val ping = createQuery(id.eq("1506341767330000"))
                .fetchOne()

        println(ping?.name)

        return ping
    }
}