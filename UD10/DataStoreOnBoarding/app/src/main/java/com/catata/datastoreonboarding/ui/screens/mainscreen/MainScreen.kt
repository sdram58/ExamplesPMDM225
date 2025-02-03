package com.catata.datastoreonboarding.ui.screens.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.catata.datastoreonboarding.model.UserData
import com.catata.datastoreonboarding.viewmodel.PreferencesViewModel

@Composable
fun MainScreen(prefsViewModel:PreferencesViewModel) {
    val user: UserData by prefsViewModel.user.observeAsState(UserData("", ""))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = user.name ?: "No user")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = user.email ?: "No email")
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            prefsViewModel.saveUser(UserData("", ""))
        }) {
            Text(text = "Borrar datos")
        }
    }

}