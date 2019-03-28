package com.seed.entities.pool

import com.seed.entities.Components
import com.seed.entities.Entity
import com.seed.entities.components.Component
import com.seed.entities.components.ComponentId
import com.seed.entities.components.ComponentId.Companion.MaxComponentSize

class BasicEntityPool(override val identityPool: IdentityPool) : EntityPool {
    private var entities = Array<Components?>(DefaultEntityPoolSize) { null }

    override fun create(vararg components: Component): Entity {
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

    private fun mapComponents(components: Array<out Component>): ArrayList<Component> {
        val compList = ArrayList<Component>(MaxComponentSize)
        compList.ensureCapacity(MaxComponentSize)

        for (i in 0 until components.size) {
            val component = components[i]
            val componentId = ComponentId.getComponentIndex(component::class)
            compList.add(componentId.index, component)
        }
        return compList
    }

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

