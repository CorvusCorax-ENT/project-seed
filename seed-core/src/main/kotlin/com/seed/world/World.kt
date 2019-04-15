package com.seed.world

import com.seed.entities.Entity
import com.seed.entities.pool.EntityPool
import com.seed.entities.systems.EntitySystem

interface World {
    val systems: List<EntitySystem> // should world decide this?

    val entityPool: EntityPool

    fun createEntity(): Entity
    fun removeEntity(entity: Entity)

    fun registerRegion(region: Region)
    fun removeRegion(region: Region)

    fun update()
}