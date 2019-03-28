package com.seed.entities.pool

class BasicIdentityPool : IdentityPool {
    private var count: Int = 0

    override fun generate(): Int {
        return ++count
    }
}