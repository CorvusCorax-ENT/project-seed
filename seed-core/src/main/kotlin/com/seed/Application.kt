package com.seed

import com.seed.entities.components.ComponentId
import com.seed.entities.components.Position
import com.seed.entities.components.Stats
import mu.KotlinLogging
import kotlin.random.Random

val logger = KotlinLogging.logger { }

class Application {

    init {
    }

    fun start() {
        logger.info { "Started!" }
    }

    companion object {
        @Suppress("UnusedMainParameter")
        @JvmStatic
        fun main(vararg args: String) {
            val component = DaggerSeedComponent.create()
            val application = component.provideApplication()

            val entityPool = component.provideEntityPool()
            for (i in 0..200) {
                val entity = entityPool.create(Position(x = Random.nextFloat(), y = Random.nextFloat()), Stats())
                val componentId = ComponentId.getComponentIndex(Position::class)
                println("Hello im entity $entity and i have a position component at ${entityPool.getComponent(entity, componentId)}")
            }
            application.start()
        }
    }
}
