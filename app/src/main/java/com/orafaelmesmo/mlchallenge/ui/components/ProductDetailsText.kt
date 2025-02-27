package com.orafaelmesmo.mlchallenge.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orafaelmesmo.mlchallenge.R
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail

@Composable
@Suppress("FunctionName")
fun ProductDetailsText(product: ProductDetail) {
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
    if (product.warranty.isNotEmpty()) {
        Text(
            text = "Garantia: {${product.warranty}}}",
            style = MaterialTheme.typography.bodyMedium,
        )
    }

    SelectionContainer {
        Text(
            text = product.descriptions,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    SelectionContainer {
        val context = LocalContext.current
        Text(
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.permalink))
                context.startActivity(intent)
            },
            text = stringResource(R.string.product_link, product.permalink),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
