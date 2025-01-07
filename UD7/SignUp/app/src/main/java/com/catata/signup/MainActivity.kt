package com.catata.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.catata.signup.ui.SignUpContent
import com.catata.signup.ui.screens.signup.SignUp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()*/
        setContent {
            SignUpContent {
                SignUp(it)
            }

        }
    }
}

