package org.example.geoblinker.presentation.features.list.devices

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun ListScreen(state: ListScreenState, onEvent: (ListScreenEvent) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(state.title) }) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.devices) { device ->
                        DeviceItem(device = device, onClick = { onEvent(ListScreenEvent.OnDeviceClick(device.imei)) })
                    }
                }
            }
        }
    }
}

@Composable
fun DeviceItem(device: DeviceUiModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = device.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = device.connectionStatus,
                    color = if (device.isOnline) Color(0xFF4CAF50) else Color.Red
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "⚡ ${device.batteryText}")
            }
            Text(text = "📍 ${device.speedText}", style = MaterialTheme.typography.body2)
        }
    }
}
