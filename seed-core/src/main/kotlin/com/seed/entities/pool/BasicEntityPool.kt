package com.seed.entities.pool

import com.seed.entities.Components
import com.seed.entities.Entity
import com.seed.entities.components.Component
import com.seed.entities.components.ComponentId
import com.seed.entities.components.ComponentId.Companion.MaxComponentSize
import com.seed.entities.components.Manifest

class BasicEntityPool(override val identityPool: IdentityPool) : EntityPool {
    private var entities = Array<Components?>(DefaultEntityPoolSize) { null }

    override fun create(vararg components: Component): Entity {
        synchronized(this) {
            return identityPool.generate().also { entity ->
                val isAvailable = if (entity >= entities.size) false else entities[entity] == null

                if (isAvailable) {
                    val compList = mapComponents(components)
                    entities[entity] = compList
                } else {
                    if (entity >= entities.size) {
                        val newArray = Array<Components?>(entities.size + PoolGrowSize) { null }
                        System.arraycopy(entities, 0, newArray, 0, entities.size)
                        entities = newArray
                        entities[entity] = mapComponents(components)
                    }
                }
            }
        }

    }

    private fun mapComponents(components: Array<out Component>): Components {
        val compMap = mutableListOf<Component>().apply {
            add(Manifest())
            addAll(components)
        }.map { Pair(ComponentId.getComponentIndex(it::class), it) }

        val array = Components(MaxComponentSize) { null }
        for (pair in compMap) {
            val componentId = pair.first
            val component = pair.second
            array[componentId.index] = component
        }
        return array
    }

    override fun size(): Int = entities.size

    override fun getComponentMap(entity: Entity): Components? {
        return entities[entity]
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Component> getComponent(entity: Entity, componentId: ComponentId<T>): T? {
        return entities[entity]?.get(componentId.index) as T?
    }

    override fun destroy(entity: Entity) {
        if (identityPool is FreeingIdentityPool)
            identityPool.free(entity)
    }
}

const val DefaultEntityPoolSize = 100
const val PoolGrowSize = 50

