package com.catata.splashscreen.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    // Para la Splash Screen
    @Serializable
    object Splash

    // Para la pantalla principal
    @Serializable
    object Main
}