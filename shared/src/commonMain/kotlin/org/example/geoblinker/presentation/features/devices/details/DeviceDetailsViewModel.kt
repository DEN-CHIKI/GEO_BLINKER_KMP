package org.example.geoblinker.presentation.features.devices.details

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.geoblinker.core.base.BaseViewModel

class DeviceDetailsViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(DeviceDetailsState())
    val state: StateFlow<DeviceDetailsState> = _state.asStateFlow()

    fun onEvent(event: DeviceDetailsEvent) {
        when (event) {
            DeviceDetailsEvent.OnEditNameClick -> _state.update { 
                it.copy(isEditingName = true, newName = it.name) 
            }
            is DeviceDetailsEvent.OnNameChange -> _state.update { 
                it.copy(newName = event.name) 
            }
            DeviceDetailsEvent.OnSaveNameClick -> saveNewName()
            DeviceDetailsEvent.OnRefreshClick -> loadDeviceDetails()
            DeviceDetailsEvent.OnBackClick -> { /* Навигация назад */ }
            DeviceDetailsEvent.OnSignalsHistoryClick -> { /* Перейти к сигналам */ }
            DeviceDetailsEvent.OnDetachDeviceClick -> { /* К экрану отвязки */ }
        }
    }

    private fun loadDeviceDetails() {
        _state.update { it.copy(isLoading = true) }
        // Имитация загрузки данных
        _state.update { it.copy(
            isLoading = false,
            name = "Мой Трекер",
            model = "Blinker v2",
            batteryLevel = 85,
            speed = "12 км/ч",
            imei = "123456789012345",
            address = "ул. Ленина, Пойковский",
            isOnline = true
        )}
    }

    private fun saveNewName() {
        val nameToSave = _state.value.newName
        if (nameToSave.isNotBlank()) {
            _state.update { it.copy(name = nameToSave, isEditingName = false) }
            println("Saved new device name: $nameToSave")
        }
    }
}
