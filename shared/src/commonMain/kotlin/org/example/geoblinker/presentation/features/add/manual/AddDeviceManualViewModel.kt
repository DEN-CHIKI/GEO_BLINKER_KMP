package org.example.geoblinker.presentation.features.add.manual

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.geoblinker.core.base.BaseViewModel

class AddDeviceManualViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(AddDeviceManualState(
        imei = "123456789012345" // Имитируем получение с прошлого экрана
    ))
    val state: StateFlow<AddDeviceManualState> = _state.asStateFlow()

    fun onEvent(event: AddDeviceManualEvent) {
        when (event) {
            is AddDeviceManualEvent.OnNameChanged -> {
                _state.update { it.copy(name = event.name, errorText = null) }
            }
            is AddDeviceManualEvent.OnCategorySelected -> {
                _state.update { it.copy(selectedCategory = event.category) }
            }
            AddDeviceManualEvent.OnFinishClick -> finalizeRegistration()
            AddDeviceManualEvent.OnBackClick -> { /* Навигация назад */ }
        }
    }

    private fun finalizeRegistration() {
        if (!_state.value.canFinish) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            // Имитируем сохранение в БД и привязку трекера
            delay(2000)
            
            _state.update { it.copy(isLoading = false, isSuccess = true) }
            println("Device ${_state.value.imei} registered as ${_state.value.name}")
        }
    }
}
