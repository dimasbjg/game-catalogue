package com.dimdimbjg.gamecatalogue.core.utils

import androidx.test.espresso.idling.CountingIdlingResource


object IddlingResource {
    private const val RESOURCE: String = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}