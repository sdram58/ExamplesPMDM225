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
import com.catata.navigationtests.navigation.First
import com.catata.navigationtests.ui.screens.layout.AppScaffold


@Composable
fun SecondScreen(navController: NavController, name: String, age: Int = 0) {
    AppScaffold(
        title = "Second Screen",
        navController = navController
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "I've navigated")
            Text(text = "Data entered:")
            //Text(text = "Name: ${if (name.isNotEmpty()) name else "No input"}")
            Text(text = "Name: ${name.ifEmpty { "No input" }}")
            Text(text = "Age: ${if (age != 0) age else "No input"}")

            Button(onClick = {
//                navController.popBackStack()

                // Goes to the indicated one (if it is the previous one it will be a new instance: empty fields)
                // Also, cleans the stack
                navController.navigate(First) {
                    popUpTo<First> {
                        inclusive = true
                    }
                }

//                navController.navigateUp()
            }) {
                Text(text = "Go back")
            }
        }
    }
}


