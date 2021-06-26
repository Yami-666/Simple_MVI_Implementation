package com.example.mviapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviapp.LoginNetworkMiddleware
import com.example.mviapp.actions.LoginAction
import com.example.mviapp.reducers.LoginReducer
import com.example.mviapp.redux.Store
import com.example.mviapp.repository.LoggingRepository
import com.example.mviapp.view_states.LoginViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // TODO: 26.06.2021 Заменить на DI
    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer(),
        middleware = listOf(
            LoggingMiddleware(),
            LoginNetworkMiddleware(
                loginRepository = LoggingRepository()
            )
        )
    )

    val viewState: StateFlow<LoginViewState> = store.state

    fun emailChanged(newEmail: String) {
        val action = LoginAction.EmailChanged(newEmail)
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun passwordChanged(newPassword: String) {
        val action = LoginAction.PasswordChanged(newPassword)
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun signInButtonClicked() {
        val action = LoginAction.SignInButtonClicked
        viewModelScope.launch {
            store.dispatch(action)
        }
    }
}