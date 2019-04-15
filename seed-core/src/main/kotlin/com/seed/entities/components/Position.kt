package com.seed.entities.components

import com.seed.math.Float3

data class Position(var position: Float3 = Float3(), val direction: Float3 = Float3()) : Component