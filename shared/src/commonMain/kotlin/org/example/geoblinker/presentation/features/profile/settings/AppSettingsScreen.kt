package org.example.geoblinker.presentation.features.profile.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppSettingsScreen(
    state: AppSettingsState,
    onEvent: (AppSettingsEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Настройки") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(AppSettingsEvent.OnBackClick) }) {
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
                .padding(16.dp)
        ) {
            Text("Внешний вид", style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.primary)
            SettingsSwitchRow(
                title = "Темная тема",
                description = "Использовать ночной режим интерфейса",
                checked = state.isDarkTheme,
                onCheckedChange = { onEvent(AppSettingsEvent.OnThemeChanged(it)) }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text("Уведомления", style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.primary)
            SettingsSwitchRow(
                title = "Push-уведомления",
                description = "Получать алерты от трекеров",
                checked = state.isPushEnabled,
                onCheckedChange = { onEvent(AppSettingsEvent.OnPushToggled(it)) }
            )

            SettingsSwitchRow(
                title = "Фоновая геолокация",
                description = "Всегда знать, где ваше устройство",
                checked = state.isLocationAlwaysOn,
                onCheckedChange = { onEvent(AppSettingsEvent.OnLocationToggled(it)) }
            )

            Spacer(modifier = Modifier.weight(1f))

            // СЕКЦИЯ: ХРАНИЛИЩЕ
            Card(
                elevation = 0.dp,
                backgroundColor = Color.LightGray.copy(alpha = 0.1f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Кеш приложения", style = MaterialTheme.typography.body1)
                        Text("Занимает: ${state.cacheSize}", style = MaterialTheme.typography.caption)
                    }
                    if (state.isLoading) {
                        CircularProgressIndicator(size = 20.dp)
                    } else {
                        IconButton(onClick = { onEvent(AppSettingsEvent.OnClearCacheClick) }) {
                            Icon(Icons.Default.DeleteSweep, contentDescription = null, tint = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsSwitchRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.body1)
            Text(text = description, style = MaterialTheme.typography.caption, color = Color.Gray)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.primary)
        )
    }
}
