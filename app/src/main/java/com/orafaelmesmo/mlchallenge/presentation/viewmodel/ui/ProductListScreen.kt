package com.orafaelmesmo.mlchallenge.presentation.viewmodel.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: SearchViewModel = koinViewModel()
    val productsPagingItems = viewModel.productsPagingData.collectAsLazyPagingItems()
    viewModel.searchProducts("bola")
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(productsPagingItems.itemCount) {
            Text( //Todo change this to a ProductItem
                text = productsPagingItems[it]?.name ?: "Name not found",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        productsPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = loadState.refresh as LoadState.Error
                    item { //Todo change this to a error item
                        Text(
                            text = "Error Loading Product: ${e.error.localizedMessage}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = loadState.append as LoadState.Error
                    item { //Todo change this to a error item
                        Text(
                            text = "Erro ao carregar mais produtos: ${e.error.localizedMessage}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
