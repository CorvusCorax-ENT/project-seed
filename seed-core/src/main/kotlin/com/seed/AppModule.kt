package com.seed

import com.seed.entities.pool.BasicEntityPool
import com.seed.entities.pool.BasicIdentityPool
import com.seed.entities.pool.EntityPool
import com.seed.entities.pool.IdentityPool
import com.seed.world.BaseWorld
import com.seed.world.World
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule {

    @Provides
    @Reusable
    fun provideApplication(): Application = Application()

    @Provides
    fun identityPool(): IdentityPool = BasicIdentityPool()

    @Provides
    @Reusable
    fun provideEntityPool(identityPool: IdentityPool): EntityPool = BasicEntityPool(identityPool)

    @Provides
    @Reusable
    fun provideWorld(entityPool: EntityPool): World = BaseWorld(entityPool)
}