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
    val owner = OwnerEntityEx4(1, "Owner")
    em.transaction.begin()
    em.persist(owner)
    em.persist(ChildEntityEx4(1, "Child", owner))
    em.transaction.commit()
    em.close()

    em = Utils.sessionFactory.createEntityManager()
    val result = em.find(OwnerEntityEx4::class.java, 1)
    println(result.id)
    println(result.data)
    println(result.children)
    em.close()
}

@Entity
@Table(name = "owner")
class OwnerEntityEx4(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    var children: Set<ChildEntityEx4> = setOf()
) {
    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , data = $data )"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as OwnerEntityEx4

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}

@Entity
@Table(name = "child")
class ChildEntityEx4(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @ManyToOne
    @JoinColumn(name = "owner_id")
    val owner: OwnerEntityEx4
) {
    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , data = $data )"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as OwnerEntityEx4

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
