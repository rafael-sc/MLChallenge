package com.orafaelmesmo.mlchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.orafaelmesmo.mlchallenge.presentation.navigation.AppNavHost
import com.orafaelmesmo.mlchallenge.ui.theme.MLChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MLChallengeTheme {
                AppNavHost()
            }
        }
    }
}
