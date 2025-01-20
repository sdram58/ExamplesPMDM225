package com.catata.splashscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.catata.splashscreen.navigation.Navigation
import com.catata.splashscreen.ui.theme.SplashScreenTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //In newer versions we don't need install SplashScreen

        /*val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            splashScreenView.remove()
            // Se vuelve a asignar el tema ya que al eliminar la Splash Screen se elimina el tema
            setTheme(R.style.Theme_SplashScreen)
            // Volver a crear la actividad para aplicar el nuevo tema
            recreate()
        }
        splashScreen.setKeepOnScreenCondition { false }*/
        enableEdgeToEdge()
        setContent {
            SplashScreenTheme {
                Navigation()
            }
        }
    }
}
