package org.example.geoblinker.presentation.features.devices.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeviceDetailsScreen(
    state: DeviceDetailsState,
    onEvent: (DeviceDetailsEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Информация об устройстве") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(DeviceDetailsEvent.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { onEvent(DeviceDetailsEvent.OnRefreshClick) }) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // КАРТОЧКА: ИМЯ И ОСНОВНОЕ (С редактированием)
            Card(elevation = 4.dp) {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                    if (state.isEditingName) {
                        OutlinedTextField(
                            value = state.newName,
                            onValueChange = { onEvent(DeviceDetailsEvent.OnNameChange(it)) },
                            label = { Text("Название устройства") },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                IconButton(onClick = { onEvent(DeviceDetailsEvent.OnSaveNameClick) }) {
                                    Icon(Icons.Default.Check, color = Color.Green, contentDescription = null)
                                }
                            }
                        )
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = state.name, style = MaterialTheme.typography.h5)
                            IconButton(onClick = { onEvent(DeviceDetailsEvent.OnEditNameClick) }) {
                                Icon(Icons.Default.Edit, modifier = Modifier.size(20.dp), contentDescription = null)
                            }
                        }
                    }
                    Text(text = "Модель: ${state.model}", style = MaterialTheme.typography.body2, color = Color.Gray)
                    Text(text = "IMEI: ${state.imei}", style = MaterialTheme.typography.caption)
                }
            }

            // КАРТОЧКА: ТЕЛЕМЕТРИЯ
            Card(elevation = 4.dp) {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                    Text("Статус и датчики", style = MaterialTheme.typography.subtitle1)
                    Divider(Modifier.padding(vertical = 8.dp))
                    
                    TelemetryRow(Icons.Default.BatteryChargingFull, "Заряд батареи", "${state.batteryLevel}%")
                    TelemetryRow(Icons.Default.Speed, "Текущая скорость", state.speed)
                    TelemetryRow(Icons.Default.LocationOn, "Адрес", state.address)
                    TelemetryRow(Icons.Default.AccessTime, "Последняя связь", state.lastSeen)
                }
            }

            // КАРТОЧКА: ДЕЙСТВИЯ
            Card(elevation = 4.dp, backgroundColor = MaterialTheme.colors.surface) {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                    TextButton(
                        onClick = { onEvent(DeviceDetailsEvent.OnSignalsHistoryClick) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.History, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("История сигналов")
                    }
                    
                    Divider()

                    TextButton(
                        onClick = { onEvent(DeviceDetailsEvent.OnDetachDeviceClick) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
                    ) {
                        Icon(Icons.Default.DeleteForever, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Отвязать устройство")
                    }
                }
            }
        }
    }
}

@Composable
private fun TelemetryRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp), tint = MaterialTheme.colors.primary)
        Spacer(Modifier.width(8.dp))
        Text(text = "$label:", style = MaterialTheme.typography.body2)
        Spacer(Modifier.weight(1f))
        Text(text = value, style = MaterialTheme.typography.body2, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    }
}
