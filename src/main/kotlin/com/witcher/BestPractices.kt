package com.witcher

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
    val children = List(10) { ChildEntity(it) }
    val owner = OwnerEntity(1)
    children.forEach { it.owner = owner }
    owner.children = children.toMutableSet()
    em.transaction.begin()
    em.persist(owner)
    owner.children.add(ChildEntity(100, "Child100").also { it.owner = owner })
    em.persist(owner)
    em.transaction.commit()
    em.close()

    em = Utils.sessionFactory.createEntityManager()
    val result = em.find(OwnerEntity::class.java, 1)
    println(result.id)
    println(result.data)
    println(result.children)
    em.close()
}

// entities classes open by all-open plugin.

@Entity
@Table(name = "owner")
class OwnerEntity(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,

    @Column(name = "data", nullable = true)
    var data: String? = null,

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    var children: MutableSet<ChildEntity> = mutableSetOf()
) {

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , data = $data )"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as OwnerEntity

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}

@Entity
@Table(name = "child")
class ChildEntity(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    var data: String? = null,
) {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    lateinit var owner: OwnerEntity

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , data = $data )"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ChildEntity

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
