package org.example.geoblinker.presentation.features.add.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AddDeviceSearchScreen(
    state: AddDeviceSearchState,
    onEvent: (AddDeviceSearchEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Добавить устройство") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(AddDeviceSearchEvent.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Введите IMEI вашего трекера",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )

            Text(
                text = "IMEI обычно указан на корпусе устройства или в коробке (15 цифр)",
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = state.imeiQuery,
                onValueChange = { onEvent(AddDeviceSearchEvent.OnImeiChanged(it)) },
                label = { Text("IMEI / ID") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                enabled = !state.isLoading
            )

            Button(
                onClick = { onEvent(AddDeviceSearchEvent.OnSearchClick) },
                enabled = state.canSearch,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(size = 24.dp, color = Color.White)
                } else {
                    Text("НАЙТИ УСТРОЙСТВО")
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(modifier = Modifier.weight(1f))
                Text(" ИЛИ ", modifier = Modifier.padding(horizontal = 8.dp), color = Color.Gray)
                Divider(modifier = Modifier.weight(1f))
            }

            OutlinedButton(
                onClick = { onEvent(AddDeviceSearchEvent.OnScanQrClick) },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Icon(Icons.Default.QrCodeScanner, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("СКАННИРОВАТЬ QR-КОД")
            }

            if (state.errorText != null) {
                Text(
                    text = state.errorText,
                    color = Color.Red,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )
            }

            if (state.isDeviceFound) {
                Text(
                    text = "Устройство найдено! Переходим к настройке...",
                    color = Color(0xFF4CAF50),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
                // В реальном приложении здесь сработает LaunchedEffect для навигации
            }
        }
    }
}
