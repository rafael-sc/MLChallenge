package com.orafaelmesmo.mlchallenge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.orafaelmesmo.mlchallenge.R

@Composable
@Suppress("FunctionName")
fun AppAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    placeHolder: Int = R.drawable.ecomerce,
    showPlaceholder: Boolean = true,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    val imageRequest =
        ImageRequest.Builder(context)
            .data(imageUrl)
            .apply {
                if (showPlaceholder) placeholder(placeHolder)
            }
            .httpHeaders(NetworkHeaders.Builder().add("User-Agent", "Mozilla/5.0").build())
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
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
        imageUrl = "https://www.anf.org.br/wp-content/uploads/2018/10/noticiasdovasco.jpg",
        contentDescription = "Imagem de exemplo",
        contentScale = ContentScale.Crop,
    )
}
