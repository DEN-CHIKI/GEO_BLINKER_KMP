package org.example.geoblinker.presentation.features.device_list
import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class DeviceUiModel(
    val imei: String,
    val name: String,
    val isConnected: Boolean,
    val connectionStatus: String,
    val batteryText: String,
    val signalIcon: String,
    val speedText: String
)

data class ListScreenState(
    val devices: List<DeviceUiModel> = emptyList(),
    val isLoading: Boolean = false
) : ViewState

sealed interface ListScreenEvent : ViewEvent {
    data object OnRefresh : ListScreenEvent
    data class OnDeviceClick(val imei: String) : ListScreenEvent
}
