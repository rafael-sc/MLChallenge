package com.orafaelmesmo.mlchallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.request.crossfade

@Suppress("FunctionName")
@Composable
fun ProductListItem(
    productName: String,
    productValue: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val model =
            ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .httpHeaders(NetworkHeaders.Builder().add("User-Agent", "Mozilla/5.0").build())
                .crossfade(true)
                .build()
        AsyncImage(
            model = model,
            contentDescription = "Imagem do produto: $productName",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .width(80.dp)
                    .height(80.dp),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = productName,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "R\$ $productValue",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
