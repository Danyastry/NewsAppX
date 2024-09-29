package com.example.newsappx

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsappx.presentation.navigation.Navigation
import com.example.newsappx.ui.theme.Background
import com.example.newsappx.ui.theme.NewsAppXTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        CoroutineScope(Dispatchers.Main).launch {
            splashScreen.setKeepOnScreenCondition { false }
        }

        enableEdgeToEdge()
        setContent {
            NewsAppXTheme {

                val systemUiController = rememberSystemUiController()
                systemUiController.isSystemBarsVisible = false
                systemUiController.isNavigationBarVisible = true
                SideEffect {
                    systemUiController.setSystemBarsColor(color = Background)
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Background)
                ) {
                    Navigation()
                }
            }
        }
    }
}
