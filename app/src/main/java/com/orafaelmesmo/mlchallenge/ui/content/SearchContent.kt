package com.orafaelmesmo.mlchallenge.ui.content

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.orafaelmesmo.mlchallenge.R
import com.orafaelmesmo.mlchallenge.presentation.ScreenState
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SearchViewModel
import com.orafaelmesmo.mlchallenge.ui.components.ProductListItemHorizontal
import com.orafaelmesmo.mlchallenge.ui.components.ProductListItemVertical

@Composable
@Suppress("FunctionName")
fun SearchContent(
    paddingValues: PaddingValues,
    viewModel: SearchViewModel,
    onProductClick: (String) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val focusManager = LocalFocusManager.current
    val productsPagingItems = viewModel.productsPagingData.collectAsLazyPagingItems()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val searchState by viewModel.searchState.collectAsState()

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (lazyListState.isScrollInProgress) {
            focusManager.clearFocus()
        }
    }

    when (searchState) {
        is ScreenState.Error -> {
            val errorMessage = (searchState as ScreenState.Error).message
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(onClick = { viewModel.retrySearch() }) {
                        Text(text = stringResource(R.string.retry))
                    }
                }
            }
        }

        is ScreenState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            if (isLandscape) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(180.dp),
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    itemsIndexed(
                        items = List(productsPagingItems.itemCount) { index -> productsPagingItems[index] },
                        key = { index, product -> product?.id ?: index },
                    ) { _, product ->
                        product?.let {
                            ProductListItemHorizontal(
                                productName = it.name,
                                productValue = it.price,
                                imageUrl = it.thumbnail,
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .clickable { onProductClick(it.id) },
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                    state = lazyListState,
                ) {
                    itemsIndexed(
                        items = List(productsPagingItems.itemCount) { index -> productsPagingItems[index] },
                        key = { index, product -> product?.id ?: index },
                    ) { _, product ->
                        product?.let {
                            ProductListItemVertical(
                                productName = it.name,
                                productValue = it.price,
                                imageUrl = it.thumbnail,
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable { onProductClick(it.id) },
                            )
                        }
                    }
                }
            }
        }
    }
}
