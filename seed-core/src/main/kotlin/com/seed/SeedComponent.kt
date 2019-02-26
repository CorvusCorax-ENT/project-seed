package com.seed

import dagger.Component

@Component(modules = [AppModule::class])
interface SeedComponent {
    fun provideApplication(): Application
}