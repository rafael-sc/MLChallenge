package com.orafaelmesmo.mlchallenge.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SettingsViewModel
import com.orafaelmesmo.mlchallenge.ui.components.SimpleTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val theme by viewModel.theme.collectAsState(initial = "light")
    val textSize by viewModel.textSize.collectAsState(initial = 1f)

    val themes = listOf("light", "dark")

    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleTopAppBar(
                detailName = "Settings",
                onBackClick = onBackClick
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Choose theme",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            themes.forEach { themeOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.saveTheme(themeOption) }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = theme == themeOption,
                        onClick = { viewModel.saveTheme(themeOption) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = themeOption.capitalize(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Text Size: ${"%.1f".format(textSize)}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Slider(
                value = textSize.toFloat(),
                onValueChange = { viewModel.saveTextSize(it) },
                valueRange = 1f..2f,
                steps = 9,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}