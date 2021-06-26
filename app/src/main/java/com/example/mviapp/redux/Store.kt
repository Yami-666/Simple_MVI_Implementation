package com.example.mviapp.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Store<S: IState, A: IAction>(
    initialState: S,
    private val reducer: IReducer<S, A>,
    private val middleware: List<IMiddleware<S, A>> = emptyList()
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    private val currentState: S
        get() = _state.value

    suspend fun dispatch(action: A) {
        middleware.forEach { middleware ->
            middleware.process(action, currentState, store = this)
        }
        val newState = reducer.reduce(
            currentState = currentState,
            action = action
        )
        _state.value = newState
    }

}