package com.seed.entities

import com.seed.entities.pool.EntityPool
import com.seed.geodata.Region

interface World {
    val entityPool: EntityPool

    fun createEntity(): Entity
    fun removeEntity(entity: Entity)

    fun registerRegion(region: Region)
    fun removeRegion(region: Region)

    fun update(deltaTime: Float)
}