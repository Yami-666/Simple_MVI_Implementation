package com.example.mviapp.login

import androidx.lifecycle.ViewModel
import com.example.mviapp.actions.LoginAction
import com.example.mviapp.reducers.LoginReducer
import com.example.mviapp.redux.Store
import com.example.mviapp.view_states.LoginViewState
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer()
    )

    val viewState: StateFlow<LoginViewState> = store.state

    fun emailChanged(newEmail: String) {
        val action = LoginAction.EmailChanged(newEmail)
        store.dispatch(action)
    }

    fun passwordChanged(newPassword: String) {
        val action = LoginAction.PasswordChanged(newPassword)
        store.dispatch(action)
    }

    fun signInButtonClicked() {
        val action = LoginAction.SignInButtonClicked
        store.dispatch(action)
    }
}