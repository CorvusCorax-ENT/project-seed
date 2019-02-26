package com.seed

class Application {

    init {
    }

    fun start() {
    }

    companion object {
        @Suppress("UnusedMainParameter")
        @JvmStatic
        fun main(vararg args: String) {
            val component = DaggerSeedComponent.create()
            val application = component.provideApplication()
            application.start()
        }
    }
}
