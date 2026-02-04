package org.example.geoblinker.presentation.features.profile.settings

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.geoblinker.core.base.BaseViewModel

class AppSettingsViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(AppSettingsState())
    val state: StateFlow<AppSettingsState> = _state.asStateFlow()

    fun onEvent(event: AppSettingsEvent) {
        when (event) {
            is AppSettingsEvent.OnThemeChanged -> {
                _state.update { it.copy(isDarkTheme = event.isDark) }
                // Тут будет вызов ThemeManager.setDarkTheme(event.isDark)
            }
            is AppSettingsEvent.OnPushToggled -> {
                _state.update { it.copy(isPushEnabled = event.isEnabled) }
            }
            is AppSettingsEvent.OnLocationToggled -> {
                _state.update { it.copy(isLocationAlwaysOn = event.isEnabled) }
            }
            AppSettingsEvent.OnClearCacheClick -> clearAppCache()
            AppSettingsEvent.OnBackClick -> { /* Навигация назад */ }
        }
    }

    private fun clearAppCache() {
        _state.update { it.copy(isLoading = true) }
        // Имитация очистки
        println("Cache cleared")
        _state.update { it.copy(isLoading = false, cacheSize = "0.0 MB") }
    }
}
