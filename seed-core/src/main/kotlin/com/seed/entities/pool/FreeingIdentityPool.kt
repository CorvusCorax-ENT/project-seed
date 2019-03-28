package com.seed.entities.pool

import com.seed.entities.Entity

interface FreeingIdentityPool : IdentityPool {
    fun free(entity: Entity)
}