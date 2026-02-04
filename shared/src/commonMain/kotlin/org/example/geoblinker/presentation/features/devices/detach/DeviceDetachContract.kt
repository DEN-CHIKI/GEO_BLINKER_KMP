package org.example.geoblinker.presentation.features.devices.detach

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class DeviceDetachState(
    val deviceId: String = "",
    val deviceName: String = "",
    val confirmInput: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorText: String? = null
) : ViewState {
    // Кнопка активна только если введенное имя совпадает с реальным
    val canDetach: Boolean = confirmInput == deviceName && !isLoading
}

sealed interface DeviceDetachEvent : ViewEvent {
    data class OnConfirmInputChanged(val text: String) : DeviceDetachEvent
    data object OnDetachConfirmClick : DeviceDetachEvent
    data object OnBackClick : DeviceDetachEvent
}
