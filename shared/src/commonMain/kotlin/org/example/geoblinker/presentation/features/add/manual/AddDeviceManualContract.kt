package org.example.geoblinker.presentation.features.add.manual

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

enum class DeviceCategory { CAR, PERSON, PET, OTHER }

data class AddDeviceManualState(
    val imei: String = "",
    val name: String = "",
    val selectedCategory: DeviceCategory = DeviceCategory.OTHER,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorText: String? = null
) : ViewState {
    val canFinish: Boolean = name.isNotBlank() && !isLoading
}

sealed interface AddDeviceManualEvent : ViewEvent {
    data class OnNameChanged(val name: String) : AddDeviceManualEvent
    data class OnCategorySelected(val category: DeviceCategory) : AddDeviceManualEvent
    data object OnFinishClick : AddDeviceManualEvent
    data object OnBackClick : AddDeviceManualEvent
}
