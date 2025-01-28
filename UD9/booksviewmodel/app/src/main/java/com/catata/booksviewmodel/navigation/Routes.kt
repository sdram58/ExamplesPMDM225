package com.catata.booksviewmodel.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    // Ruta para la lista de libros
    @Serializable
    object Main

    // Ruta para la información de un libro
    @Serializable
    object BookInfo
}



