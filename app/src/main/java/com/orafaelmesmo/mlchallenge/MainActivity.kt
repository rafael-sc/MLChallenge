package com.orafaelmesmo.mlchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orafaelmesmo.mlchallenge.data.remote.RetrofitClient
import com.orafaelmesmo.mlchallenge.data.repository.ProductRepositoryImpl
import com.orafaelmesmo.mlchallenge.domain.SearchProductsUseCase
import com.orafaelmesmo.mlchallenge.presentation.theme.MLChallengeTheme
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SearchViewModel

class MainActivity : ComponentActivity() {
    val searchViewModel = SearchViewModel(
        searchProductsUseCase = SearchProductsUseCase(
            repository = ProductRepositoryImpl(
                apiService = RetrofitClient.apiService
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MLChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
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
    MLChallengeTheme {
        Greeting("Mercado Livre")
    }
}