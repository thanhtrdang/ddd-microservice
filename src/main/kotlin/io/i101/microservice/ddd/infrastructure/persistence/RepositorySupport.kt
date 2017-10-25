package io.i101.microservice.ddd.infrastructure.persistence

import com.querydsl.core.types.Predicate
import com.querydsl.jpa.JPQLQuery
import io.i101.library.ddd.Entity
import io.i101.library.ddd.Repository
import io.i101.microservice.ddd.springBean
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport.getEntityInformation
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository
import reactor.core.publisher.Mono
import java.io.Serializable
import javax.persistence.EntityManager

@Suppress("UNCHECKED_CAST")
abstract class RepositorySupport<T: Entity<T, ID>, ID: Serializable>(aggregateRootClass: Class<T>, entityManager: EntityManager = springBean(EntityManager::class.java)):
        QuerydslJpaRepository<T, ID>(getEntityInformation<T>(aggregateRootClass, entityManager) as JpaEntityInformation<T, ID>, entityManager),
        Repository<T, ID> {

    override final fun find(id: ID): Mono<T> = Mono.defer {
        return@defer Mono.justOrEmpty(findById(id))
    }

    override final fun store(entity: T): Mono<T> = Mono.defer {
        return@defer Mono.justOrEmpty(saveAndFlush(entity))
    }

// TODO - Define later, see ReactiveSortingRepository
//    final fun find(predicate: Predicate): Mono<T> = Mono.defer {
//        return@defer Mono.justOrEmpty(findOne(predicate))
//    }

    override final fun createQuery(vararg predicate: Predicate): JPQLQuery<T> {
        return super.createQuery(*predicate) as JPQLQuery<T>
    }

    override final fun createCountQuery(vararg predicate: Predicate): JPQLQuery<T> {
        return super.createCountQuery(*predicate) as JPQLQuery<T>
    }
}