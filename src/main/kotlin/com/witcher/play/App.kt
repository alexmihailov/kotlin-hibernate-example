package com.witcher.play

import com.witcher.ChildEntity
import com.witcher.OwnerEntity
import play.test.Helpers
import kotlin.system.exitProcess

fun main() {
    val app = Helpers.fakeApplication()
    try {
        val repository = app.injector().instanceOf(Repository::class.java)

        val children = List(10) { ChildEntity(it, "Child$it") }
        val owner = OwnerEntity(1)
        children.forEach { it.owner = owner }
        owner.children = children.toMutableSet()
        repository.create(owner).toCompletableFuture().join()

//        repository.runInSession { em ->
//            val result = em.find(OwnerEntity::class.java, 1)
//            println(result.id)
//            println(result.data)
//            println(result.children)
//        }

        val result = repository.get(1).toCompletableFuture().join().get();
        println(result.id)
        println(result.data)
        println(result.children)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        exitProcess(0)
    }
}
