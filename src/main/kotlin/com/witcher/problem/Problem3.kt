package com.witcher.problem

import com.witcher.Utils

fun main() {
    var em = Utils.sessionFactory.createEntityManager()
    val owner = OwnerEntity(1, "Owner")
    em.transaction.begin()
    em.persist(owner)
    em.persist(ChildEntity(1, "Child", owner))
    em.transaction.commit()
    em.close()

    em = Utils.sessionFactory.createEntityManager()
    val result = em.find(OwnerEntity::class.java, 1)
    println(result.id)
    println(result.data)
    println(result.children)
    em.close()
}
