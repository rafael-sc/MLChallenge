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
import androidx.compose.ui.unit.dp

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
        AppAsyncImage(
            imageUrl = imageUrl,
            contentDescription = "Imagem do produto: $productName",
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
                text = productValue,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
