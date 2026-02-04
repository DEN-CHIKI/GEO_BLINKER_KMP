package org.example.geoblinker.presentation.features.add.search

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class AddDeviceSearchState(
    val imeiQuery: String = "",
    val isLoading: Boolean = false,
    val errorText: String? = null,
    val isDeviceFound: Boolean = false
) : ViewState {
    // Поиск разрешен, если введено минимум 10 символов
    val canSearch: Boolean = imeiQuery.length >= 10 && !isLoading
}

sealed interface AddDeviceSearchEvent : ViewEvent {
    data class OnImeiChanged(val imei: String) : AddDeviceSearchEvent
    data object OnSearchClick : AddDeviceSearchEvent
    data object OnScanQrClick : AddDeviceSearchEvent
    data object OnBackClick : AddDeviceSearchEvent
}
