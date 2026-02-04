package org.example.geoblinker.presentation.features.profile.edit

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.geoblinker.core.base.BaseViewModel

class ProfileEditViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(ProfileEditState())
    val state: StateFlow<ProfileEditState> = _state.asStateFlow()

    init {
        loadCurrentProfile()
    }

    fun onEvent(event: ProfileEditEvent) {
        when (event) {
            is ProfileEditEvent.OnNameChanged -> _state.update { it.copy(name = event.name) }
            is ProfileEditEvent.OnEmailChanged -> _state.update { it.copy(email = event.email) }
            ProfileEditEvent.OnSaveClick -> saveProfile()
            ProfileEditEvent.OnBackClick -> { /* Навигация назад */ }
        }
    }

    private fun loadCurrentProfile() {
        // Имитируем получение текущих данных
        val currentName = "Денис"
        val currentEmail = "denis@poikovsky.ru"
        _state.update { it.copy(
            initialName = currentName,
            name = currentName,
            email = currentEmail
        ) }
    }

    private fun saveProfile() {
        if (!_state.value.isSaveEnabled) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            // Имитируем сетевой запрос
            delay(1500)
            
            _state.update { it.copy(isLoading = false, isSuccess = true) }
            println("Profile updated: ${_state.value.name}")
        }
    }
}
