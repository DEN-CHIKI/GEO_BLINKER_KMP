package org.example.geoblinker.presentation.features.devices.detach

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DeviceDetachScreen(
    state: DeviceDetachState,
    onEvent: (DeviceDetachEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Отвязка устройства") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(DeviceDetachEvent.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                Icons.Default.Warning,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(64.dp)
            )

            Text(
                text = "Внимание!",
                style = MaterialTheme.typography.h5,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Вы собираетесь удалить устройство \"${state.deviceName}\". Вся история перемещений и сигналов будет стерта безвозвратно.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Для подтверждения введите название устройства:",
                style = MaterialTheme.typography.caption
            )

            OutlinedTextField(
                value = state.confirmInput,
                onValueChange = { onEvent(DeviceDetachEvent.OnConfirmInputChanged(it)) },
                label = { Text(state.deviceName) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = state.confirmInput.isNotEmpty() && !state.canDetach
            )

            Button(
                onClick = { onEvent(DeviceDetachEvent.OnDetachConfirmClick) },
                enabled = state.canDetach,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(size = 24.dp, color = Color.White)
                } else {
                    Text("УДАЛИТЬ НАВСЕГДА")
                }
            }
            
            if (state.isSuccess) {
                Text("Устройство успешно отвязано", color = Color(0xFF4CAF50))
            }
        }
    }
}
