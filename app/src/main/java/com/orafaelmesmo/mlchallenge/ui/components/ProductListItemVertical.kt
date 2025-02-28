package com.orafaelmesmo.mlchallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orafaelmesmo.mlchallenge.R

@Suppress("FunctionName")
@Composable
fun ProductListItemVertical(
    modifier: Modifier = Modifier,
    productName: String,
    productValue: String,
    imageUrl: String,
) {
    Card(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            AppAsyncImage(
                imageUrl = imageUrl,
                contentDescription = stringResource(R.string.product_image, productName),
                modifier =
                Modifier
                    .size(120.dp)
                    .padding(8.dp),
            )
            Column(
                modifier =
                Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = productName,
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = productValue,
                    style = MaterialTheme.typography.displaySmall,
                )
            }
        }
    }
}
