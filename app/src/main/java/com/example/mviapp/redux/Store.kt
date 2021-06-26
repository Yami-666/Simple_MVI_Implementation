package com.example.mviapp.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Store<S: State, A: Action>(
    initialState: S,
    private val reducer: Reducer<S, A>
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    /**
     * Функция, которая принимает новое действие пользователя и передаёт его редюсеру
     * вместе с текущим стейтов. Редюсер возвращает новый стейт для стора.
     * */
    fun dispatch(action: A) {
        val currentState = state.value
        val newState = reducer.reduce(
            currentState = currentState,
            action = action
        )
        _state.value = newState
    }

}