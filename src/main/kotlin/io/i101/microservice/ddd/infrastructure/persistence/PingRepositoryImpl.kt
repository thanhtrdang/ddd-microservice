package io.i101.microservice.ddd.infrastructure.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import io.i101.microservice.ddd.domain.model.ping.PingEntity
import io.i101.microservice.ddd.domain.model.ping.PingRepository
import io.i101.microservice.ddd.domain.model.ping.QPingEntity.pingEntity

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class PingRepositoryImpl(val entityManager: EntityManager):
        RepositorySupport<PingEntity, String>(PingEntity::class.java, entityManager), PingRepository {
    override fun findXXX() {
        pingEntity.apply {
            val x = JPAQueryFactory(entityManager)
                    .selectFrom(this)
                    .where(id.eq("1506341767330000"))
                    .fetchOne()

            println(x?.name)
        }
    }
}