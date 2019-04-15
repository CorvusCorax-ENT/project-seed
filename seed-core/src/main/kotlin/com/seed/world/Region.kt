package com.seed.world

import com.seed.entities.Entity
import com.seed.entities.components.Position

interface Region {
    val regionEntities: List<Entity>

    fun canMove(position: Position) = true // Permanently true for now
}