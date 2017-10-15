package io.i101.microservice.ddd.domain.model.ping

import io.i101.library.ddd.EntitySupport
import io.i101.library.ddd.IDGenerator
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PING_TBL")
open class PingEntity (@Id override val id: String = IDGenerator.next, val name: String, val age: Int):
        EntitySupport<PingEntity, String>()