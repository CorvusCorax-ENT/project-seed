package com.seed.desires

import com.seed.entities.components.Component

class Actor: Component {
    var currentDesire: Desire = DesireIdle(1f)
       // private set

    var previousDesire: Desire = currentDesire
        // private set
}