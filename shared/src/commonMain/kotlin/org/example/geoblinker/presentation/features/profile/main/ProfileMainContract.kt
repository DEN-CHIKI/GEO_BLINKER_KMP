package org.example.geoblinker.presentation.features.profile.main

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class ProfileMainState(
    val isLoading: Boolean = false,
    val phoneNumber: String = "",
    val userName: String = "Пользователь",
    val devicesCount: Int = 0,
    val appVersion: String = "1.0.0-alpha",
    val isNotificationsEnabled: Boolean = true
) : ViewState

sealed interface ProfileMainEvent : ViewEvent {
    data object OnEditProfileClick : ProfileMainEvent
    data object OnSettingsClick : ProfileMainEvent
    data object OnLogoutClick : ProfileMainEvent
    data object OnSupportClick : ProfileMainEvent
}
