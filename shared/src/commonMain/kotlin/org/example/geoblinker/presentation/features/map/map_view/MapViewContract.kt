package org.example.geoblinker.presentation.features.map.map_view

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class MapViewState(
    val markers: List<MapMarkerUiModel> = emptyList(),
    val centerLat: Double = 0.0,
    val centerLon: Double = 0.0,
    val zoomLevel: Float = 10f,
    val isFollowingUser: Boolean = false,
    val mapUrl: String = "" // Ссылка для WebView
) : ViewState

data class MapMarkerUiModel(
    val id: String,
    val lat: Double,
    val lon: Double,
    val title: String,
    val iconRes: String,
    val isSelected: Boolean = false
)

sealed interface MapViewEvent : ViewEvent {
    data class OnMarkerClick(val id: String) : MapViewEvent
    data class OnMapBoundsChanged(val lat: Double, val lon: Double, val zoom: Float) : MapViewEvent
    data object OnMapLoaded : MapViewEvent
}
