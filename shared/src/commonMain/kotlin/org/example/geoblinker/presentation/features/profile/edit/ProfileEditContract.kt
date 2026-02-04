package org.example.geoblinker.presentation.features.profile.edit

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class ProfileEditState(
    val isLoading: Boolean = false,
    val initialName: String = "",
    val name: String = "",
    val email: String = "",
    val isSuccess: Boolean = false,
    val errorText: String? = null
) : ViewState {
    // Сохранение доступно, если имя не пустое и данные отличаются от исходных
    val isSaveEnabled: Boolean = name.isNotBlank() && (name != initialName) && !isLoading
}

sealed interface ProfileEditEvent : ViewEvent {
    data class OnNameChanged(val name: String) : ProfileEditEvent
    data class OnEmailChanged(val email: String) : ProfileEditEvent
    data object OnSaveClick : ProfileEditEvent
    data object OnBackClick : ProfileEditEvent
}
