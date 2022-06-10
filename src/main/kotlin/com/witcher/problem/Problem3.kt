package com.witcher.problem

import com.witcher.Utils
import org.hibernate.Hibernate
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
    val owner = OwnerEntityEx3(1, "Owner")
    em.transaction.begin()
    em.persist(owner)
    em.persist(ChildEntityEx3(1, "Child", owner))
    em.transaction.commit()
    em.close()

    em = Utils.sessionFactory.createEntityManager()
    val result = em.find(OwnerEntityEx3::class.java, 1)
    println(result.id)
    println(result.data)
    println(result.children)
    em.close()
}

@Entity
@Table(name = "owner")
data class OwnerEntityEx3(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    val children: Set<ChildEntityEx3> = setOf()
) {
    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , data = $data )"
    }
}

@Entity
@Table(name = "child")
data class ChildEntityEx3(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @ManyToOne
    @JoinColumn(name = "owner_id")
    val owner: OwnerEntityEx3
) {
//    @Override
//    override fun toString(): String {
//        return this::class.simpleName + "(id = $id , data = $data )"
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
//        other as ChildEntityEx3
//
//        return id == other.id
//    }
//
//    override fun hashCode(): Int = id.hashCode()
}
