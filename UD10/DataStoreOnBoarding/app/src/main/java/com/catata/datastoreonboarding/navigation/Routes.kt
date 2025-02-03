package com.catata.datastoreonboarding.navigation

import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    object SplashScreen

    @Serializable
    object Onboarding1Screen

    @Serializable
    data class Onboarding2Screen(
        val name: String
    )

    @Serializable
    data class MainScreen(
        val name: String,
        val email: String
    )
}