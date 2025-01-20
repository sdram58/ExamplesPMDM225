package com.catata.splashscreen.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.catata.splashscreen.R
import com.catata.splashscreen.navigation.Main
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(5000)
        navController.popBackStack() // Evitar volver a la Splash Screen
        navController.navigate(Main)
    }

    Splash()
}

@Composable
fun Splash() {
    var animateAlpha by rememberSaveable { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if(animateAlpha) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ),
        label = "alpha animation"
    )
    var greetingVisible by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        animateAlpha = true
        delay(2000)
        greetingVisible = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id= R.drawable.fourstarsball),
            contentDescription = "logo",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .alpha(alpha)
        )
        AnimatedVisibility(visible = greetingVisible) {
            Text(
                "Welcomed",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}