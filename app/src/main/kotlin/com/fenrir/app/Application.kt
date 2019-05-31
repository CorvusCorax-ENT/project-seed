package com.fenrir.app

import com.fenrir.app.dependency.AppComponent
import com.fenrir.app.dependency.DaggerAppComponent
import com.fenrir.app.dependency.modules.AppModule

class Application {

    val appComponent: AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    private fun start() {
        println("Hello friend")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val application = Application()
            application.start()
        }
    }

}