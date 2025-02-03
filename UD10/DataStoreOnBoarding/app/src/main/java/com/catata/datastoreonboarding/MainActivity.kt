package com.catata.datastoreonboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.catata.datastoreonboarding.navigation.Navigation
import com.catata.datastoreonboarding.ui.theme.DataStoreOnBoardingTheme
import com.catata.datastoreonboarding.viewmodel.PreferencesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            DataStoreOnBoardingTheme {
                val preferencesViewModel: PreferencesViewModel by viewModels()
                Navigation(preferencesViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataStoreOnBoardingTheme {
        Greeting("Android")
    }
}