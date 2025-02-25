package com.orafaelmesmo.mlchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Suppress("FunctionName")
fun ImageCarouselWithControls(
    modifier: Modifier = Modifier,
    images: List<String>,
    pagerState: PagerState,
    onImageClick: (Int) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(300.dp),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            AppAsyncImage(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clickable { onImageClick(page) },
                imageUrl = images[page],
                contentDescription = "Imagem ${page + 1} do produto",
            )
        }

        Text(
            text = "${pagerState.currentPage + 1} / ${images.size}",
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.5f)),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
        )

        if (pagerState.currentPage > 0) {
            IconButton(
                onClick = onPreviousClick,
                modifier = Modifier.align(Alignment.CenterStart),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Imagem anterior",
                    tint = Color.White,
                )
            }
        }

        if (pagerState.currentPage < images.size - 1) {
            IconButton(
                onClick = onNextClick,
                modifier = Modifier.align(Alignment.CenterEnd),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = "Próxima imagem",
                    tint = Color.White,
                )
            }
        }
    }
}
