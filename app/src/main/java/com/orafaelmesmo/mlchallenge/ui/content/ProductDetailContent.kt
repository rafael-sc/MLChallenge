package com.orafaelmesmo.mlchallenge.ui.content

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import com.orafaelmesmo.mlchallenge.ui.components.FullScreenImageDialog
import com.orafaelmesmo.mlchallenge.ui.components.ImageCarouselWithControls
import com.orafaelmesmo.mlchallenge.ui.components.ProductDetailsText
import kotlinx.coroutines.launch

@Composable
@Suppress("FunctionName")
fun ProductDetailContent(
    product: ProductDetail,
    modifier: Modifier = Modifier,
) {
    var isFullScreenImageOpen by remember { mutableStateOf(false) }
    var selectedImageIndex by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = { product.images.size })
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isFullScreenImageOpen) {
        FullScreenImageDialog(
            images = product.images,
            initialIndex = selectedImageIndex,
            onDismiss = { isFullScreenImageOpen = false },
        )
    }

    if (isLandscape) {
        Row(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(modifier = Modifier.weight(1f)) {
                ImageCarouselWithControls(
                    modifier = Modifier.fillMaxSize(),
                    images = product.images,
                    pagerState = pagerState,
                    onImageClick = { index ->
                        selectedImageIndex = index
                        isFullScreenImageOpen = true
                    },
                    onPreviousClick = {
                        if (pagerState.currentPage > 0) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    },
                    onNextClick = {
                        if (pagerState.currentPage < product.images.size - 1) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                )
            }
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ProductDetailsText(product)
            }
        }
    } else {
        Column(
            modifier =
                modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
        ) {
            ImageCarouselWithControls(
                images = product.images,
                pagerState = pagerState,
                onImageClick = { index ->
                    selectedImageIndex = index
                    isFullScreenImageOpen = true
                },
                onPreviousClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                onNextClick = {
                    if (pagerState.currentPage < product.images.size - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ProductDetailsText(product)
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xdedede,
    showSystemUi = true,
    device = Devices.PIXEL_4,
)
@Composable
@Suppress("FunctionName")
fun ProductDetailContentPreview() {
    val product =
        ProductDetail(
            id = "1",
            name = "Produto Exemplo",
            price = "100",
            originalPrice = "15000",
            attributes = listOf("Atributo 1", "Atributo 2"),
            warranty = "1 ano",
            descriptions = "Descrição do produto exemplo.",
            permalink = "http://example.com",
            images = listOf("http://example.com/image1.jpg", "http://example.com/image2.jpg"),
        )
    ProductDetailContent(product = product)
}
