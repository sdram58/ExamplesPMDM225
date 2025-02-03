package com.catata.datastoreonboarding.ui.screens.onboarding

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.catata.datastoreonboarding.model.UserData
import com.catata.datastoreonboarding.navigation.Routes
import com.catata.datastoreonboarding.viewmodel.PreferencesViewModel

@Composable
fun OnBoarding1(navController: NavController, prefsViewModel: PreferencesViewModel) {

    var name by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hola, por favor introduce tu nombre")
        TextField(value = name,
                  onValueChange = {name = it},
                  label = { Text(text = "Nombre")}
        )

        Button(onClick = {
            prefsViewModel.saveUser(UserData(name, ""))
            navController.navigate(Routes.Onboarding2Screen(name = name))
        }) {
            Text(text = "Next")
        }
    }
}