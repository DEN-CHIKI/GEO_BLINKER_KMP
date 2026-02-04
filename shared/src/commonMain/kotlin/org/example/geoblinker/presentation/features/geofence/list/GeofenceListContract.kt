package org.example.geoblinker.presentation.features.geofence.list

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class GeofenceItem(
    val id: String,
    val name: String,
    val radius: Int, // в метрах
    val isActive: Boolean,
    val isNotifyOnEntry: Boolean,
    val isNotifyOnExit: Boolean
)

data class GeofenceListState(
    val geofences: List<GeofenceItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorText: String? = null
) : ViewState

sealed interface GeofenceListEvent : ViewEvent {
    data object OnAddClick : GeofenceListEvent
    data class OnToggleActive(val id: String, val isActive: Boolean) : GeofenceListEvent
    data class OnDeleteClick(val id: String) : GeofenceListEvent
    data class OnItemClick(val id: String) : GeofenceListEvent
    data object OnBackClick : GeofenceListEvent
}
