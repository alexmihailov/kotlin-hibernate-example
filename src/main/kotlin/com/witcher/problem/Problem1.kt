package com.witcher.problem

import com.witcher.Utils
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

fun main() {
    var em = Utils.sessionFactory.createEntityManager()
    em.transaction.begin()
    em.persist(OwnerEntityEx1(1, "Owner"))
    em.transaction.commit()
    em.close()

    em = Utils.sessionFactory.createEntityManager()
    val result = em.find(OwnerEntityEx1::class.java, 1)
    println(result.id)
    println(result.data)
    println(result.children)
    em.close()
}

@Entity
@Table(name = "owner")
data class OwnerEntityEx1(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    val children: List<ChildEntityEx1> = listOf()
)

@Entity
@Table(name = "child")
data class ChildEntityEx1(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @ManyToOne
    @JoinColumn(name = "owner_id")
    val owner: OwnerEntityEx1
)
