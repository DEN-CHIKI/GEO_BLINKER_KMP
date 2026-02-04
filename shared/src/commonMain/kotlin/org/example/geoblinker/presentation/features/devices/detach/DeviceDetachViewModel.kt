package org.example.geoblinker.presentation.features.devices.detach

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.geoblinker.core.base.BaseViewModel

class DeviceDetachViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(DeviceDetachState(
        deviceId = "dev_777",
        deviceName = "Мой Трекер" // В реале придет через навигацию
    ))
    val state: StateFlow<DeviceDetachState> = _state.asStateFlow()

    fun onEvent(event: DeviceDetachEvent) {
        when (event) {
            is DeviceDetachEvent.OnConfirmInputChanged -> {
                _state.update { it.copy(confirmInput = event.text, errorText = null) }
            }
            DeviceDetachEvent.OnDetachConfirmClick -> detachProcess()
            DeviceDetachEvent.OnBackClick -> { /* Назад */ }
        }
    }

    private fun detachProcess() {
        if (!_state.value.canDetach) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            // Имитируем запрос к API
            delay(2000)
            
            _state.update { it.copy(isLoading = false, isSuccess = true) }
            println("Device ${_state.value.deviceId} detached successfully")
        }
    }
}
