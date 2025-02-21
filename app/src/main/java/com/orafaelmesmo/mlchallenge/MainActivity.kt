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
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SearchViewModel
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.ui.ProductListScreen
import com.orafaelmesmo.mlchallenge.ui.theme.MLChallengeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel.searchProducts("power ball vermelha")
        enableEdgeToEdge()
        setContent {
            MLChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductListScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MLChallengeTheme {
        Greeting("Mercado Livre")
    }
}
