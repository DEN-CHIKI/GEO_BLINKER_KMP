package org.example.geoblinker.presentation.features.profile.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileMainScreen(
    state: ProfileMainState,
    onEvent: (ProfileMainEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Личный кабинет") })
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // СЕКЦИЯ: ИНФОРМАЦИЯ О ЮЗЕРЕ
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colors.primary
            )
            Text(text = state.userName, style = MaterialTheme.typography.h6)
            Text(text = state.phoneNumber, style = MaterialTheme.typography.body2, color = Color.Gray)

            Spacer(modifier = Modifier.height(24.dp))

            // КАРТОЧКА: СТАТИСТИКА
            Card(elevation = 2.dp, modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Активных устройств:")
                    Text(text = state.devicesCount.toString(), fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // МЕНЮ НАСТРОЕК
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileMenuItem(Icons.Default.Edit, "Редактировать профиль") { onEvent(ProfileMainEvent.OnEditProfileClick) }
                ProfileMenuItem(Icons.Default.Settings, "Настройки приложения") { onEvent(ProfileMainEvent.OnSettingsClick) }
                ProfileMenuItem(Icons.Default.HelpOutline, "Поддержка") { onEvent(ProfileMainEvent.OnSupportClick) }
            }

            Spacer(modifier = Modifier.weight(1f))

            // ВЕРСИЯ И ВЫХОД
            Text(text = "Версия: ${state.appVersion}", style = MaterialTheme.typography.caption, color = Color.Gray)
            
            TextButton(
                onClick = { onEvent(ProfileMainEvent.OnLogoutClick) },
                colors = ButtonDefaults.textButtonColors(contentColor = Color.Red),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("ВЫЙТИ ИЗ АККАУНТА")
            }
        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onSurface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(16.dp))
            Text(text = title, style = MaterialTheme.typography.body1)
            Spacer(Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}
