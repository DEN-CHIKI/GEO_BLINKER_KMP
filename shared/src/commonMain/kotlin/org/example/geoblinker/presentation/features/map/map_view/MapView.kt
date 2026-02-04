package org.example.geoblinker.presentation.features.map.map_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MapView(
    state: MapViewState,
    onEvent: (MapViewEvent) -> Unit
) {
    // В будущем здесь будет expect fun NativeMapView
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0)) // Цвет карты
    ) {
        // Имитация маркеров на экране
        state.markers.forEach { marker ->
            Box(
                modifier = Modifier
                    .offset(x = (marker.lat % 100).dp, y = (marker.lon % 100).dp)
                    .size(40.dp)
                    .background(if (marker.isSelected) Color.Red else Color.Blue)
            ) {
                Text(
                    text = marker.title.take(1),
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Индикатор загрузки WebView
        if (state.mapUrl.isEmpty()) {
            Text(
                "Подключение к картографическому сервису...",
                modifier = Modifier.align(Alignment.Center).padding(16.dp)
            )
        }
    }
}
