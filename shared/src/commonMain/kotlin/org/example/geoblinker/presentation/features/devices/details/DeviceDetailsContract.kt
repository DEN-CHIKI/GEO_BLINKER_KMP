package org.example.geoblinker.presentation.features.devices.details

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class DeviceDetailsState(
    val isLoading: Boolean = false,
    val imei: String = "",
    val name: String = "",
    val model: String = "",
    val batteryLevel: Int = 0,
    val speed: String = "0 км/ч",
    val lastSeen: String = "",
    val address: String = "Определяется...",
    val isOnline: Boolean = false,
    val isEditingName: Boolean = false,
    val newName: String = "",
    val errorText: String? = null
) : ViewState

sealed interface DeviceDetailsEvent : ViewEvent {
    data object OnBackClick : DeviceDetailsEvent
    data object OnEditNameClick : DeviceDetailsEvent
    data class OnNameChange(val name: String) : DeviceDetailsEvent
    data object OnSaveNameClick : DeviceDetailsEvent
    data object OnRefreshClick : DeviceDetailsEvent
    data object OnSignalsHistoryClick : DeviceDetailsEvent
    data object OnDetachDeviceClick : DeviceDetailsEvent
}
