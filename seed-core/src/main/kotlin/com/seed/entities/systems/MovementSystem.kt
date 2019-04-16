package com.seed.entities.systems

import com.seed.desires.DesireIdle
import com.seed.desires.MoveTo
import com.seed.entities.*
import com.seed.entities.pool.EntityPool
import com.seed.math.*
import kotlin.math.roundToInt

class MovementSystem(entityPool: EntityPool) : IteratingSystem(entityPool) {
    override fun requires(entity: Entity, components: Components): Boolean {
        return components.has(PositionIndex)
    }

    override fun update(time: Long, entity: Entity) {
        val actor = getComponent(entity, ActorIndex)
        val currentDesire = actor.currentDesire as? MoveTo ?: return

        val positionComponent = getComponent(entity, PositionIndex)
        val position = positionComponent.position
        val toPosition = currentDesire.toPosition
        val direction = normalize(toPosition - position)
        // val desire = currentDesire.value

        // TODO check with region
        // region.canMove

        val distance = distance(position, toPosition).roundToInt()
        if (distance <= 0) {
            actor.currentDesire = DesireIdle()
        }
        else {
            val ray = Ray(position, direction)
            positionComponent.position = pointAt(ray, .1f) // TODO compute step count based on desire
        }

        println("Pawn to ${positionComponent.position} dist $distance")
    }
}