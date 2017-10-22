package io.i101.microservice.ddd.infrastructure.persistence

import io.i101.microservice.ddd.domain.model.ping.PingEntity
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import io.i101.microservice.ddd.domain.model.ping.QPingEntity.pingEntity
import org.springframework.stereotype.Repository

@Repository
class PingRepositoryImpl: RepositorySupport<PingEntity, String>(PingEntity::class.java),
        PingRepository {

    override fun findEntity(): PingEntity = with(pingEntity) {
        val ping = createQuery(id.eq("0001508662774334"))
                .fetchOne()

        println("${ping?.name} - ${ping?.email?.value}")

        return ping
    }

    override fun findValueObject(): PingEntity = with(pingEntity) {
        val ping = createQuery(email.value.eq("thanhtrdang@gmail.com"))
                .fetchOne()

        println("${ping?.name} - ${ping?.email?.value}")

        return ping
    }
}