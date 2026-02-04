package org.example.geoblinker.presentation.features.geofence.list

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.geoblinker.core.base.BaseViewModel

class GeofenceListViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(GeofenceListState())
    val state: StateFlow<GeofenceListState> = _state.asStateFlow()

    init {
        loadGeofences()
    }

    fun onEvent(event: GeofenceListEvent) {
        when (event) {
            is GeofenceListEvent.OnToggleActive -> toggleGeofence(event.id, event.isActive)
            is GeofenceListEvent.OnDeleteClick -> deleteGeofence(event.id)
            GeofenceListEvent.OnAddClick -> { /* Навигация на создание */ }
            is GeofenceListEvent.OnItemClick -> { /* Навигация на детали/редактирование */ }
            GeofenceListEvent.OnBackClick -> { /* Назад */ }
        }
    }

    private fun loadGeofences() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(1000) // Имитация сети/БД
            
            val mockData = listOf(
                GeofenceItem("1", "Дом (Пойковский)", 200, true, true, true),
                GeofenceItem("2", "Работа (Вахта)", 500, false, false, true),
                GeofenceItem("3", "Школа №4", 150, true, true, false)
            )
            
            _state.update { it.copy(geofences = mockData, isLoading = false) }
        }
    }

    private fun toggleGeofence(id: String, active: Boolean) {
        _state.update { currentState ->
            val updated = currentState.geofences.map {
                if (it.id == id) it.copy(isActive = active) else it
            }
            currentState.copy(geofences = updated)
        }
        println("Geofence $id activity changed to: $active")
    }

    private fun deleteGeofence(id: String) {
        _state.update { currentState ->
            val updated = currentState.geofences.filter { it.id != id }
            currentState.copy(geofences = updated)
        }
    }
}
