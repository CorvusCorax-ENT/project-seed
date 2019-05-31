package com.seed

import com.seed.desires.MoveTo
import com.seed.entities.ActorIndex
import com.seed.math.Float3
import mu.KotlinLogging

val logger = KotlinLogging.logger { }

class Application {

    fun start() {
        logger.info { "Started!" }
    }

    companion object {
        @Suppress("UnusedMainParameter")
        @JvmStatic
        fun main(vararg args: String) {
            val component = DaggerSeedComponent.create()
            val application = component.provideApplication()
            val entity = component.provideWorld().createEntity()
            val pool = component.provideEntityPool()

            val actor = pool.getComponent(entity, ActorIndex) ?: return
            actor.influence(MoveTo(Float3(12f, 0f, -12f), 1f))
            application.start()
        }
    }
}
