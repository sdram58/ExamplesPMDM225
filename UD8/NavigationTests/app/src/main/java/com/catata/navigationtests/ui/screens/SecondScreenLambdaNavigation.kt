package com.catata.navigationtests.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.catata.navigationtests.ui.screens.layout.AppScaffold

@Composable
fun SecondScreenLambdaNavigation(navController: NavController, name: String, age: Int = 0, navigateBack: () -> Unit) {
    AppScaffold(
        title = "Second Screen",
        navController = navController
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "I've navigated")
            Text(text = "Data entered:")
            Text(text = "Name: ${if (name.isNotEmpty()) name else "No input data"}")
            Text(text = "Age: ${if (age != 0) age else "No input data"}")

            Button(onClick = {
                navigateBack()
            }) {
                Text(text = "Go Back")
            }
        }
    }
}


