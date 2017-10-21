package io.i101.microservice.ddd.infrastructure.persistence

import io.i101.microservice.ddd.domain.model.ping.PingEntity
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import io.i101.microservice.ddd.domain.model.ping.QPingEntity.pingEntity
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class PingRepositoryImpl(entityManager: EntityManager): RepositorySupport<PingEntity, String>(PingEntity::class.java, entityManager),
        PingRepository {

    override fun findXXX() {
        pingEntity.apply {
            val x = createQuery(id.eq("1506341767330000"))
                    .fetchOne()

            println(x?.name)
        }
    }
}