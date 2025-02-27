package com.orafaelmesmo.mlchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.orafaelmesmo.mlchallenge.presentation.navigation.AppNavHost
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SettingsViewModel
import com.orafaelmesmo.mlchallenge.ui.theme.MLChallengeTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: SettingsViewModel = koinViewModel()
            val theme by viewModel.theme.collectAsState(initial = "light")
            val textSize by viewModel.textSize.collectAsState(initial = 1f)

            MLChallengeTheme(darkTheme = theme == "dark", textSize = textSize) {
                AppNavHost()
            }
        }
    }
}
