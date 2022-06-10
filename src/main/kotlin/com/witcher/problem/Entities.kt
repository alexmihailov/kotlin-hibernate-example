package com.witcher.problem

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "owner")
data class OwnerEntity(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    val children: List<ChildEntity>? = listOf()
)

@Entity
@Table(name = "child")
data class ChildEntity(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,
    @Column(name = "data", nullable = true)
    val data: String?,
    @ManyToOne
    @JoinColumn(name = "owner_id")
    val owner: OwnerEntity
) {
    override fun toString(): String {
        return "ChildEntity(id=$id, data=$data, ownerId=${owner.id})"
    }
}
