package com.seed.desires

import com.seed.math.Float3

data class MoveTo(
        val toPosition: Float3,
        override var value: Float = 0f
) : Desire