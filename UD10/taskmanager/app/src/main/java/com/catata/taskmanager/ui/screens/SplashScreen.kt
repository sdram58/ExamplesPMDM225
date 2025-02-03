package com.catata.taskmanager.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.catata.taskmanager.R
import com.catata.taskmanager.navigation.Routes
import com.catata.taskmanager.tasks.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, taskViewModel: TaskViewModel) {
    Splash()

    LaunchedEffect(key1 = true) {
        delay(5000)
        taskViewModel.getAllTasks()
        navController.popBackStack() // Evitar volver a la Splash Screen
        navController.navigate(Routes.Main)
    }
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
        Text(
            "Gestor de tareas",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id= R.drawable.rick),
            contentDescription = "Rick Sanchez",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .alpha(alpha)
                .clip(CircleShape)
                .border(
                    width = 10.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )
        AnimatedVisibility(visible = greetingVisible) {
            Text(
                text ="By Rick Sanchez",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




