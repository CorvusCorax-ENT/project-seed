package com.seed.entities.systems

import com.seed.entities.Components
import com.seed.entities.Entity
import com.seed.entities.ManifestIndex
import com.seed.entities.components.Component
import com.seed.entities.components.ComponentId
import com.seed.entities.pool.EntityPool
import java.lang.IllegalStateException
import java.lang.RuntimeException

abstract class EntitySystem(private val entityPool: EntityPool) {
    private var entitiesSnapshot = emptyList<Entity>()

    private var entitiesToRemove: MutableList<Entity>? = null
    private var entitiesToAdd: HashSet<Entity>? = null

    fun addEntity(entity: Entity) {
        val toAdd = entitiesToAdd ?: HashSet()

        if (!toAdd.add(entity) || entitiesSnapshot.contains(entity))
            throw IllegalStateException("Entity already present!")

        if (entitiesToAdd == null)
            entitiesToAdd = toAdd
    }

    fun removeEntity(entity: Entity) {
        if (entity >= entitiesSnapshot.size)
            return // nothing to add

        val toRemove = entitiesToRemove ?: mutableListOf()

        if (!toRemove.remove(entity))
            toRemove.add(entity)

        if (entitiesToRemove == null)
            entitiesToRemove = toRemove
    }

    fun update() {
        checkForModifications()

        val time = System.currentTimeMillis() // TODO World.time
        update(time, entitiesSnapshot)
    }

    private fun checkForModifications() {
        val toAdd = entitiesToAdd
        val toRemove = entitiesToRemove

        if (toAdd != null || toRemove != null) { // TODO mby hot spot
            val list = entitiesSnapshot.toMutableList()

            toRemove?.let { list.removeAll(it) }
            toAdd?.let { list.addAll(it) }

            entitiesSnapshot = list

            entitiesToAdd = null
            entitiesToRemove = null
        }
    }

    abstract fun requires(entity: Entity, components: Components): Boolean
    abstract fun update(time: Long, entities: List<Entity>)

    /**
     * Will throw exception if the requirements were not preset
     */
    fun <T: Component> getComponent(entity: Entity, componentId: ComponentId<T>): T = entityPool.getComponent(entity, componentId) as T
}