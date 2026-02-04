package org.example.geoblinker.presentation.features.add.search

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.geoblinker.core.base.BaseViewModel

class AddDeviceSearchViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(AddDeviceSearchState())
    val state: StateFlow<AddDeviceSearchState> = _state.asStateFlow()

    fun onEvent(event: AddDeviceSearchEvent) {
        when (event) {
            is AddDeviceSearchEvent.OnImeiChanged -> {
                // Очищаем ввод от всего, кроме цифр
                val filtered = event.imei.filter { it.isDigit() }
                if (filtered.length <= 15) { // Ограничение IMEI
                    _state.update { it.copy(imeiQuery = filtered, errorText = null) }
                }
            }
            AddDeviceSearchEvent.OnSearchClick -> performSearch()
            AddDeviceSearchEvent.OnScanQrClick -> { /* Открыть камеру */ }
            AddDeviceSearchEvent.OnBackClick -> { /* Навигация назад */ }
        }
    }

    private fun performSearch() {
        val query = _state.value.imeiQuery
        if (query.length < 10) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorText = null) }
            
            // Имитируем запрос к серверу
            delay(1500)
            
            if (query == "123456789012345") {
                _state.update { it.copy(isLoading = false, isDeviceFound = true) }
            } else if (query == "111111111111111") {
                _state.update { it.copy(isLoading = false, errorText = "Устройство уже привязано к другому аккаунту") }
            } else {
                _state.update { it.copy(isLoading = false, errorText = "Устройство с таким IMEI не найдено") }
            }
        }
    }
}
