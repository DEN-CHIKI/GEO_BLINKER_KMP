package org.example.geoblinker.presentation.features.device_list

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.foundation.layout.*

@Composable
fun ListScreen(state: ListScreenState, onEvent: (ListScreenEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = state.title, modifier = Modifier.padding(16.dp))
        
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(state.devices) { device ->
                    DeviceItem(device = device, onClick = { onEvent(ListScreenEvent.OnDeviceClick(device.imei)) })
                }
            }
        }
    }
}

@Composable
fun DeviceItem(device: DeviceUiModel, onClick: () -> Unit) {
    // Здесь будет красивая карточка устройства из твоего старого кода, 
    // но адаптированная под чистый State
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = device.name)
        Text(text = if (device.isOnline) "🟢 ${device.connectionStatus}" else "🔴 ${device.connectionStatus}")
        Text(text = "Скорость: ${device.speedText}")
    }
}
