package com.seed.world

import com.seed.desires.Actor
import com.seed.entities.Entity
import com.seed.entities.components.Position
import com.seed.entities.components.Stats
import com.seed.entities.pool.EntityPool
import com.seed.entities.systems.EntitySystem
import com.seed.entities.systems.MovementSystem
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

const val WorldTicks = 10
const val WorldTicksPerSecond =  1000L / WorldTicks

class BaseWorld(override val entityPool: EntityPool) : World {
    private val scheduler = Executors.newSingleThreadScheduledExecutor()

    override val systems: List<EntitySystem> = mutableListOf(
            MovementSystem(entityPool)
    )

    init {
        scheduler.scheduleAtFixedRate({ update() }, 0, WorldTicksPerSecond, TimeUnit.MILLISECONDS)
    }

    override fun createEntity(): Entity {
        val entity = entityPool.create(
                Actor(),
                Position(),
                Stats()
        )

        systems.filter { it.requires(entity, entityPool.getComponentMap(entity) ?: return@filter false) }
                .forEach { it.addEntity(entity) }

        return entity
    }

    override fun removeEntity(entity: Entity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerRegion(region: Region) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeRegion(region: Region) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update() {
        systems.forEach { it.update() }
    }

    fun shutdown() = scheduler.shutdown()
}