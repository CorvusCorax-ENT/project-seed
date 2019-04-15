package com.seed.entities

import com.seed.desires.Actor
import com.seed.entities.components.*

typealias Entity = Int
typealias Components = Array<Component?>

fun <T: Component> Components.has(vararg comps: ComponentId<T>): Boolean {
    for (i in comps) {
        if (this[i.index] == null)
            return false
    }

    return true
}

val ManifestIndex = ComponentId.getComponentIndex(Manifest::class)
val ActorIndex = ComponentId.getComponentIndex(Actor::class)
val PositionIndex = ComponentId.getComponentIndex(Position::class)
val StatsIndex = ComponentId.getComponentIndex(Stats::class)
