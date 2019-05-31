package com.fenrir.app.dependency

import com.fenrir.app.Application
import com.fenrir.app.dependency.modules.AppModule
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent: AppExposes {
    fun provideApplication(): Application
}