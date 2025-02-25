package com.orafaelmesmo.mlchallenge.presentation.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SearchViewModel
import com.orafaelmesmo.mlchallenge.ui.components.AppTopBar
import com.orafaelmesmo.mlchallenge.ui.components.SearchContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit,
    viewModel: SearchViewModel = koinViewModel()
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(
            state = rememberTopAppBarState(),
        )
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopBar(
                scrollBehavior = scrollBehavior,
            ) {
                viewModel.searchProducts(it)
            }
        },
    ) { paddingValues ->
        SearchContent(
            paddingValues = paddingValues,
            viewModel = viewModel,
            onProductClick = onProductClick,
        )
    }
}