package com.orafaelmesmo.mlchallenge.presentation.viewmodel.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SearchViewModel
import com.orafaelmesmo.mlchallenge.ui.components.AppTopBar
import com.orafaelmesmo.mlchallenge.ui.components.ProductListItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionName")
@Composable
fun ProductListScreen(modifier: Modifier = Modifier) {
    val viewModel: SearchViewModel = koinViewModel()
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(
            state = rememberTopAppBarState(),
        )
    Scaffold(
        modifier =
        modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
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
        )
    }
}

@Suppress("FunctionName")
@Composable
fun SearchContent(
    paddingValues: PaddingValues,
    viewModel: SearchViewModel,
) {
    val lazyListState = rememberLazyListState()
    val productsPagingItems = viewModel.productsPagingData.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding =
        PaddingValues(
            top = paddingValues.calculateTopPadding() + 16.dp,
        ),
        state = lazyListState,
    ) {
        items(
            count = productsPagingItems.itemCount,
            key = { index ->
                productsPagingItems[index]?.id ?: index
            },
        ) { index ->
            val product = productsPagingItems[index]
            product?.let {
                ProductListItem(
                    productName = it.name ,
                    productValue = it.price,
                    imageUrl = it.thumbnail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
