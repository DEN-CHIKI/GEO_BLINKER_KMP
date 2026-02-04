package org.example.geoblinker.presentation.features.add.manual

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddDeviceManualScreen(
    state: AddDeviceManualState,
    onEvent: (AddDeviceManualEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Настройка устройства") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(AddDeviceManualEvent.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "IMEI: ${state.imei}",
                style = MaterialTheme.typography.caption,
                color = Color.Gray
            )

            OutlinedTextField(
                value = state.name,
                onValueChange = { onEvent(AddDeviceManualEvent.OnNameChanged(it)) },
                label = { Text("Название (напр. Моя Машина)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Text("Выберите категорию:", style = MaterialTheme.typography.subtitle1)

            val categories = listOf(
                DeviceCategory.CAR to Icons.Default.DirectionsCar,
                DeviceCategory.PERSON to Icons.Default.Person,
                DeviceCategory.PET to Icons.Default.Pets,
                DeviceCategory.OTHER to Icons.Default.Extension
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(categories) { (cat, icon) ->
                    val isSelected = state.selectedCategory == cat
                    Card(
                        elevation = if (isSelected) 4.dp else 1.dp,
                        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colors.primary) else null,
                        modifier = Modifier.height(100.dp),
                        onClick = { onEvent(AddDeviceManualEvent.OnCategorySelected(cat)) }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(icon, contentDescription = null, tint = if (isSelected) MaterialTheme.colors.primary else Color.Gray)
                            Text(cat.name, style = MaterialTheme.typography.caption)
                        }
                    }
                }
            }

            Button(
                onClick = { onEvent(AddDeviceManualEvent.OnFinishClick) },
                enabled = state.canFinish,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(size = 24.dp, color = Color.White)
                } else {
                    Text("ЗАВЕРШИТЬ ПРИВЯЗКУ")
                }
            }
        }
    }
}
