@file:OptIn(ExperimentalMaterial3Api::class)

package com.orafaelmesmo.mlchallenge.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orafaelmesmo.mlchallenge.R
import com.orafaelmesmo.mlchallenge.presentation.ScreenState
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.DetailsViewModel
import com.orafaelmesmo.mlchallenge.ui.content.ProductDetailContent
import org.koin.androidx.compose.koinViewModel

@Composable
@Suppress("FunctionName")
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = koinViewModel(),
    productId: String,
) {
    LaunchedEffect(productId) {
        viewModel.getProductsDetails(productId)
    }
    val productDetailState = viewModel.productDetail.collectAsState()
    val screenState = viewModel.searchState.collectAsState()

    when (val state = screenState.value) {
        ScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ScreenState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(R.string.error_prefix) + " ${state.message}",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

        else -> {
            productDetailState.value?.let { detail ->
                Scaffold(
                    modifier = modifier,
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.heightIn(min = 56.dp, max = Dp.Unspecified),
                            title = {
                                Text(
                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp),
                                    textAlign = TextAlign.Start,
                                    text = detail.name,
                                    maxLines = 3,
                                    overflow = TextOverflow.Visible,
                                    style = MaterialTheme.typography.headlineSmall,
                                )
                            },
                        )
                    },
                ) { paddingValues ->
                    ProductDetailContent(
                        modifier = Modifier.padding(paddingValues),
                        product = detail,
                    )
                }
            } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.no_details_available),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
