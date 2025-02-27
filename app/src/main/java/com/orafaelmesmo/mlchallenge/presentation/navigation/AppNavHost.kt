package com.orafaelmesmo.mlchallenge.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orafaelmesmo.mlchallenge.presentation.ui.ProductDetailScreen
import com.orafaelmesmo.mlchallenge.presentation.ui.ProductListScreen
import com.orafaelmesmo.mlchallenge.presentation.ui.SettingsScreen

@Composable
@Suppress("FunctionName")
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "product_list",
    ) {
        composable("product_list") {
            Scaffold { innerPadding ->
                ProductListScreen(
                    modifier = Modifier.padding(innerPadding),
                    onProductClick = { productId ->
                        navController.navigate("product_detail/$productId")
                    },
                    onSettingsClick = {
                        navController.navigate("settings_screen")
                    },
                )
            }
        }

        composable(route = "settings_screen") {
            Scaffold { innerPadding ->
                SettingsScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
        }

        composable(
            route = "product_detail/{productId}",
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(
                productId = productId,
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
