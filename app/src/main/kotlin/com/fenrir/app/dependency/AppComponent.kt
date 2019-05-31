package com.fenrir.app.dependency

import com.fenrir.app.Application
import com.fenrir.app.dependency.modules.AppModule
import com.fenrir.app.dependency.modules.SeedModule
import dagger.Component

@Component(modules = [AppModule::class, SeedModule::class])
interface AppComponent: AppExposes {
    fun provideApplication(): Application
}