package org.example.geoblinker.presentation.features.map.map_view

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.geoblinker.core.base.BaseViewModel

class MapViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(MapViewState())
    val state: StateFlow<MapViewState> = _state.asStateFlow()

    init {
        // Загружаем тестовые данные для карты
        mockLoadDevices()
    }

    fun onEvent(event: MapViewEvent) {
        when (event) {
            is MapViewEvent.OnMarkerClick -> selectMarker(event.id)
            is MapViewEvent.OnMapBoundsChanged -> updateBounds(event.lat, event.lon, event.zoom)
            MapViewEvent.OnMapLoaded -> _state.update { it.copy(mapUrl = "file:///android_asset/map.html") }
        }
    }

    private fun mockLoadDevices() {
        val mockMarkers = listOf(
            MapMarkerUiModel("1", 55.751244, 37.618423, "Машина", "ic_car"),
            MapMarkerUiModel("2", 55.755826, 37.617300, "Велосипед", "ic_bike")
        )
        _state.update { it.copy(markers = mockMarkers) }
    }

    private fun selectMarker(id: String) {
        _state.update { currentState ->
            currentState.copy(
                markers = currentState.markers.map { it.copy(isSelected = it.id == id) }
            )
        }
        println("Marker selected: $id")
    }

    private fun updateBounds(lat: Double, lon: Double, zoom: Float) {
        _state.update { it.copy(centerLat = lat, centerLon = lon, zoomLevel = zoom) }
    }
}
