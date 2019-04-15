package com.seed.entities.systems

import com.seed.desires.DesireIdle
import com.seed.desires.MoveTo
import com.seed.entities.*
import com.seed.entities.pool.EntityPool
import com.seed.math.Ray
import com.seed.math.distance
import com.seed.math.normalize
import com.seed.math.pointAt

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

        // TODO check with region
        // region.canMove

        // val desire = currentDesire.value
        if (distance(position, toPosition) <= 1f) {
            actor.currentDesire = DesireIdle()
            return
        }

        val ray = Ray(position, direction)
        positionComponent.position = pointAt(ray, 1f) // TODO compute step count based on desire
        println("Pawn to ${positionComponent.position}")
    }
}