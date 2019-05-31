package com.seed.desires

import com.seed.entities.components.Component

class Actor: Component {
    var currentDesire: Desire = DesireIdle(0.5f)
        private set

    var previousDesire: Desire = currentDesire
        private set

    fun changeDesire(desire: Desire) {
        previousDesire = currentDesire
        currentDesire = desire
    }

    // TODO maybe a pool/queue
    fun influence(desire: Desire) = if (desire.value > currentDesire.value) changeDesire(desire) else Unit
}