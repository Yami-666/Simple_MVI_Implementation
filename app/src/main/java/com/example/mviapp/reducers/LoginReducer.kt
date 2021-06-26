package com.example.mviapp.reducers

import android.util.Log
import com.example.mviapp.actions.LoginAction
import com.example.mviapp.redux.Reducer
import com.example.mviapp.view_states.LoginViewState

class LoginReducer : Reducer<LoginViewState, LoginAction> {
    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        Log.v("LoginReducer", "Processing action: $action")

        return when (action) {
            is LoginAction.EmailChanged -> {
                processEmailChanged(currentState, action)
            }
            is LoginAction.PasswordChanged -> {
                processPasswordChanged(currentState, action)
            }
            is LoginAction.LoginStarted -> {
                processLoginStarted(currentState)
            }
            is LoginAction.LoginFailed -> {
                processLoginFailed(currentState)
            }
            is LoginAction.LoginCompleted -> {
                processLoginCompleted(currentState)
            }
            is LoginAction.SignInButtonClicked -> currentState
        }
    }

    private fun processLoginFailed(currentState: LoginViewState) =
        currentState.copy(
            showProgressBar = false
        )

    private fun processLoginCompleted(currentState: LoginViewState) =
        currentState.copy(
            showProgressBar = false
        )

    private fun processLoginStarted(currentState: LoginViewState) =
        currentState.copy(
            showProgressBar = true
        )

    private fun processPasswordChanged(
        currentState: LoginViewState,
        action: LoginAction.PasswordChanged
    ) = currentState.copy(
        password = action.newPassword
    )

    private fun processEmailChanged(
        currentState: LoginViewState,
        action: LoginAction.EmailChanged
    ) = currentState.copy(
        email = action.newEmail
    )
}