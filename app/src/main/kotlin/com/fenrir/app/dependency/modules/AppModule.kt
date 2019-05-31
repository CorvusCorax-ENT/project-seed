package com.fenrir.app.dependency.modules

import com.fenrir.app.Application
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule(private val application: Application) {

    @Provides
    @Reusable
    fun provideApplication(): Application = application
}