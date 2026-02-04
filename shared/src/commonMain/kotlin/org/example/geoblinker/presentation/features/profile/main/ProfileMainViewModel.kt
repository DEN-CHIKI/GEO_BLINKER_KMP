package org.example.geoblinker.presentation.features.profile.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.geoblinker.core.base.BaseViewModel

class ProfileMainViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(ProfileMainState())
    val state: StateFlow<ProfileMainState> = _state.asStateFlow()

    init {
        loadUserProfile()
    }

    fun onEvent(event: ProfileMainEvent) {
        when (event) {
            ProfileMainEvent.OnEditProfileClick -> { /* Навигация к редактированию */ }
            ProfileMainEvent.OnSettingsClick -> { /* Навигация к настройкам */ }
            ProfileMainEvent.OnLogoutClick -> handleLogout()
            ProfileMainEvent.OnSupportClick -> { /* Открыть почту или чат */ }
        }
    }

    private fun loadUserProfile() {
        _state.update { it.copy(isLoading = true) }
        // Имитируем получение данных из локального хранилища
        _state.update { it.copy(
            isLoading = false,
            phoneNumber = "+7 (900) 000-00-00",
            userName = "Денис",
            devicesCount = 3,
            appVersion = "1.0.1-stable"
        )}
    }

    private fun handleLogout() {
        println("User logged out. Clearing local session...")
        // Здесь будет вызов Repository.clearAuth()
    }
}
