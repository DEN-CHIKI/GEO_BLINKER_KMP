package org.example.geoblinker.presentation.features.profile.settings

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class AppSettingsState(
    val isLoading: Boolean = false,
    val isDarkTheme: Boolean = false,
    val isPushEnabled: Boolean = true,
    val isLocationAlwaysOn: Boolean = false,
    val cacheSize: String = "12.4 MB"
) : ViewState

sealed interface AppSettingsEvent : ViewEvent {
    data class OnThemeChanged(val isDark: Boolean) : AppSettingsEvent
    data class OnPushToggled(val isEnabled: Boolean) : AppSettingsEvent
    data class OnLocationToggled(val isEnabled: Boolean) : AppSettingsEvent
    data object OnClearCacheClick : AppSettingsEvent
    data object OnBackClick : AppSettingsEvent
}
