package com.witcher.play

import com.witcher.OwnerEntity
import org.taymyr.play.repository.infrastructure.persistence.DatabaseExecutionContext
import org.taymyr.play.repository.infrastructure.persistence.JPARepository
import play.db.jpa.JPAApi
import javax.inject.Inject
import javax.persistence.EntityManager


class Repository @Inject constructor(
    jpaApi: JPAApi,
    executionContext: DatabaseExecutionContext
) : JPARepository<OwnerEntity, Int>(jpaApi, executionContext, OwnerEntity::class.java,) {

    override fun nextIdentity(): Int {
        TODO("Not yet implemented")
    }

    fun <E> runInSession(function: (EntityManager) -> E): E = transaction(function)
}
