package com.orafaelmesmo.mlchallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orafaelmesmo.mlchallenge.R

@Suppress("FunctionName")
@Composable
fun ProductListItemHorizontal(
    productName: String,
    productValue: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppAsyncImage(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                imageUrl = imageUrl,
                contentDescription =  stringResource(R.string.product_image, productName),
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier =
                    Modifier
                        .padding(top = 8.dp),
                text = productName,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier =
                    Modifier
                        .padding(top = 4.dp),
                text = productValue,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("FunctionName")
fun ProductListItemHorizontalPreview() {
    ProductListItemHorizontal(
        productName = "Produto de exemplo",
        productValue = "R$ 100,00",
        imageUrl = "https://www.anf.org.br/wp-content/uploads/2018/10/noticiasdovasco.jpg",
    )
}
