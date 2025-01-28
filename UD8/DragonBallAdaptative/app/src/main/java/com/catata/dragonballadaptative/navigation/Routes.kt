package com.catata.dragonballadaptative.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    // Ruta para la lista de personajes en móviles
    @Serializable
    object CharacterList

    // Ruta para la información de un personaje en móviles
    @Serializable
    data class CharacterInfo(
        val characterId: Int = 0
    )

    // Ruta para el maestro-detalle en pantallas grandes o móviles en horizontal
    @Serializable
    object MasterDetail
}



