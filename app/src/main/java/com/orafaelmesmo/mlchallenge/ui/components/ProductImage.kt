package com.orafaelmesmo.mlchallenge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
@Suppress("FunctionName")
fun AppAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Inside,
) {
    val context = LocalContext.current
    val imageRequest =
        ImageRequest.Builder(context)
            .data(imageUrl)
            .httpHeaders(NetworkHeaders.Builder().add("User-Agent", "Mozilla/5.0").build())
            .crossfade(true)
            .build()
    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}

@Preview(showBackground = true)
@Composable
@Suppress("FunctionName")
fun AppAsyncImagePreview() {
    AppAsyncImage(
        imageUrl = "https://via.placeholder.com/150",
        contentDescription = "Imagem de exemplo",
        contentScale = ContentScale.Crop,
    )
}
