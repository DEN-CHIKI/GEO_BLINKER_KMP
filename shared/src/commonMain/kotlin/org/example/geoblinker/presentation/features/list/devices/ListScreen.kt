package org.example.geoblinker.presentation.features.list.devices

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment

@Composable
fun ListScreen(state: ListScreenState, onEvent: (ListScreenEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(state.title) })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.devices) { device ->
                        DeviceItem(
                            device = device, 
                            onClick = { onEvent(ListScreenEvent.OnDeviceClick(device.imei)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DeviceItem(device: DeviceUiModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 2.dp,
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = device.name, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    color = if (device.isOnline) Color(0xFF4CAF50) else Color.LightGray,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = device.connectionStatus,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )
                }
            }
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = "🔋 ${device.batteryText}", style = MaterialTheme.typography.body2)
                Text(text = "🚀 ${device.speedText}", style = MaterialTheme.typography.body2)
            }
        }
    }
}
