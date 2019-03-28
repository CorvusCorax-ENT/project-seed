package com.seed.entities.components

import kotlin.reflect.KClass


class ComponentId<T: Component> private constructor() {
    val index: Int = MaxComponentSize++

    companion object {
        /**
         * Simplest way just to get started working on this.
         */
        @JvmStatic
        var MaxComponentSize = 0
            private set

        @JvmStatic
        private val componentTypeMapping = mutableMapOf<KClass<out Component>, ComponentId<out Component>>()

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun <T: Component> getComponentIndex(type: KClass<T>): ComponentId<T> {
            return componentTypeMapping.getOrPut(type) { ComponentId<T>() } as ComponentId<T>
        }
    }
}


