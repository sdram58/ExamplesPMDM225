package com.catata.navigationtests.navigation

import kotlinx.serialization.Serializable


// If the screen DOES NOT need to receive values, it is defined with: object
@Serializable
object First

// If the screen NEEDS to receive values, it is defined with: data class
@Serializable
data class Second(
    // mandatory parameter
    val name: String,
    // optional parameter
    val age: Int = 0
)



