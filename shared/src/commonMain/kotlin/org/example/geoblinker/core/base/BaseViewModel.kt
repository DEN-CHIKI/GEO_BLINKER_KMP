package org.example.geoblinker.core.base
import kotlinx.coroutines.flow.*
import org.example.geoblinker.presentation.platform.PlatformViewModel

abstract class BaseViewModel<S : ViewState, E : ViewEvent, Ef : ViewEffect>(initialState: S) : PlatformViewModel() {
    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
    abstract fun onEvent(event: E)
}
