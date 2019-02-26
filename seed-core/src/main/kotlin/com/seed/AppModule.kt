package com.seed

import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule {

    @Provides
    @Reusable
    fun provideApplication(): Application = Application()
}