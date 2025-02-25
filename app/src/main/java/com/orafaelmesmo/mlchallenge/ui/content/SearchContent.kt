package com.orafaelmesmo.mlchallenge.ui.content

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (lazyListState.isScrollInProgress) {
            focusManager.clearFocus()
        }
    }

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
            productsPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally),
                            )
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally),
                            )
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        item {
                            Text(
                                text = "Error Loading Product: ${e.error.localizedMessage}",
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = loadState.append as LoadState.Error
                        item {
                            Text(
                                text = "Error Loading Product: ${e.error.localizedMessage}",
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                            )
                        }
                    }
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

            productsPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally),
                            )
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally),
                            )
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        item {
                            Text(
                                text = "Error Loading Product: ${e.error.localizedMessage}",
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = loadState.append as LoadState.Error
                        item {
                            Text(
                                text = "Error Loading Product: ${e.error.localizedMessage}",
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
