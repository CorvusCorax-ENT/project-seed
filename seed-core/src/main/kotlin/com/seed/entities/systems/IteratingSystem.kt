package com.seed.entities.systems

import com.seed.entities.Entity
import com.seed.entities.ManifestIndex
import com.seed.entities.pool.EntityPool

abstract class IteratingSystem(entityPool: EntityPool) : EntitySystem(entityPool) {
    final override fun update(time: Long, entities: List<Entity>) {
        entities.forEach { entity ->
            val manifest = getComponent(entity, ManifestIndex)
            if (manifest.isActive)
                update(time, entity)
        }
    }

    abstract fun update(time: Long, entity: Entity)
}