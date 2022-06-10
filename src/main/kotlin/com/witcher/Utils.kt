package com.witcher

import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

object Utils {
    val sessionFactory: EntityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit")
}
