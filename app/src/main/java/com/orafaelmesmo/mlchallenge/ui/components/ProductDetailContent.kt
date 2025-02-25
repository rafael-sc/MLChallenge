package com.orafaelmesmo.mlchallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail

@Composable
@Suppress("FunctionName")
fun ProductDetailContent(
    product: ProductDetail,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = { product.images.size })

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
    ) {
        if (product.images.isNotEmpty()) {
            HorizontalPager(
                pageSize = PageSize.Fill,
                state = pagerState,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(300.dp),
            ) { page ->
                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(product.images[page])
                            .crossfade(true)
                            .build(),
                    contentDescription = "Imagem ${page + 1} do produto ${product.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                if (product.originalPrice.isNotEmpty() && product.originalPrice != product.price) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = product.originalPrice,
                        style =
                            MaterialTheme.typography.bodyMedium.copy(
                                textDecoration = TextDecoration.LineThrough,
                            ),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    )
                }
            }
            product.attributes.forEach { attribute ->
                Text(
                    text = attribute,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Text(
                text = product.warranty,
                style = MaterialTheme.typography.bodyMedium,
            )

            SelectionContainer {
                Text(
                    text = product.descriptions,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Text(
                text = "Link do produto: ${product.permalink}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
