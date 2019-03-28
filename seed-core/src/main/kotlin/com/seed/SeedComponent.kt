package com.seed

import com.seed.entities.pool.EntityPool
import dagger.Component

@Component(modules = [AppModule::class])
interface SeedComponent {
    fun provideApplication(): Application

    fun provideEntityPool(): EntityPool
}