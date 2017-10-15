package io.i101.microservice.ddd.infrastructure.persistence

import io.i101.library.ddd.Entity
import io.i101.library.ddd.Repository
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport.getEntityInformation
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository
import reactor.core.publisher.Mono
import java.io.Serializable
import javax.persistence.EntityManager

@Suppress("UNCHECKED_CAST")
abstract class RepositorySupport<T: Entity<T, ID>, ID: Serializable>(aggregateRootClass: Class<T>, entityManager: EntityManager):
        QuerydslJpaRepository<T, ID>(getEntityInformation<T>(aggregateRootClass, entityManager) as JpaEntityInformation<T, ID>, entityManager),
        Repository<T, ID> {

    override final fun find(id: ID): Mono<T> {
        return Mono.justOrEmpty(findById(id))
    }

    //@Transactional
    override final fun store(entity: T): Mono<T> {
        return Mono.justOrEmpty(saveAndFlush(entity))
    }

}