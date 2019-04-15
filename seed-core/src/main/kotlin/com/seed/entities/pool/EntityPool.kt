package com.seed.entities.pool

import com.seed.entities.Components
import com.seed.entities.Entity
import com.seed.entities.components.Component
import com.seed.entities.components.ComponentId

interface EntityPool {
    val identityPool: IdentityPool

    fun create(vararg components: Component): Entity
    fun getComponentMap(entity: Entity): Components?
    fun <T : Component> getComponent(entity: Entity, componentId: ComponentId<T>): T?
    fun destroy(entity: Entity)
    fun size(): Int
}