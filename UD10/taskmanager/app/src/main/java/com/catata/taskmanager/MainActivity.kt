package com.catata.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.catata.taskmanager.navigation.Navigation
import com.catata.taskmanager.tasks.ui.viewmodel.TaskViewModel
import com.catata.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                val taskViewModel by viewModels<TaskViewModel>()
                Navigation(taskViewModel)
            }
        }
    }
}