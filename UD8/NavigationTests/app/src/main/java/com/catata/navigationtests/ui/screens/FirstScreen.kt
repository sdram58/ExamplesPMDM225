package com.catata.navigationtests.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.catata.navigationtests.navigation.Routes
import com.catata.navigationtests.ui.screens.layout.AppScaffold


@Composable
fun FirstScreen(navController: NavController) {
    AppScaffold(
        title = "First Screen",
        navController = navController
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "App for navigation")
            Spacer(modifier = Modifier.height(20.dp))
            var nameState by rememberSaveable { mutableStateOf("")}
            TextField(
                value = nameState,
                onValueChange = { nameState = it },
                placeholder = { Text(text = "Type your name") }
            )
            Spacer(modifier = Modifier.height(20.dp))
            var ageState by rememberSaveable { mutableStateOf("")}
            TextField(
                value = ageState,
                onValueChange = { ageState = it },
                placeholder = { Text(text = "Type your age") }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.navigate(
                        Routes.Second(
                            name = nameState,
                            age = ageState.toIntOrNull() ?: 0
                        )
                    )
                }
            ) {
                Text(text = "Move next window")
            }
        }
    }
}





































//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FirstScreen(navController: NavController) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("First Screen") }
//            )
//        },
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize().padding(it),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "App para navegar")
//            var nameState by rememberSaveable { mutableStateOf("") }
//            TextField(
//                value = nameState,
//                onValueChange = { nameState = it },
//                placeholder = { Text(text = "Introduce tu nombre")}
//            )
//            Button(
//                onClick = {
//                    navController.navigate(route = Routes.SecondScreen.createRoute(nameState))
//                    nameState = ""
//                },
//                enabled = nameState.isNotEmpty()
//            ) {
//                Text(text = "Navega a la segunda ventana")
//            }
//
////            var timesRemain by rememberSaveable { mutableStateOf(5) }
////            BackHandler(timesRemain>0) {
////                /* Realizar acciones */
////                timesRemain--
////            }
//        }
//    }
//}
