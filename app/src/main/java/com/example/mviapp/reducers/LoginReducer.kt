package com.example.mviapp.reducers

import android.util.Log
import com.example.mviapp.actions.LoginAction
import com.example.mviapp.redux.IReducer
import com.example.mviapp.view_states.LoginViewState
import com.example.mviapp.view_states.LoginViewState.Companion.getStateWithoutError

class LoginReducer : IReducer<LoginViewState, LoginAction> {
    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        Log.v("LoginReducer", "Processing action: $action")

        return when (action) {
            is LoginAction.EmailChanged -> processEmailChanged(currentState, action)
            is LoginAction.PasswordChanged -> processPasswordChanged(currentState, action)
            is LoginAction.LoginStarted -> processLoginStarted(currentState)
            is LoginAction.LoginFailed -> processLoginFailed(currentState)
            is LoginAction.LoginCompleted -> processLoginCompleted(currentState)
            is LoginAction.SignInButtonClicked -> processSignInButtonClicked(currentState)
            is LoginAction.InvalidEmailSubmitted -> processInvalidEmail(currentState)
        }
    }

    private fun processInvalidEmail(currentState: LoginViewState) =
        currentState.copy(
            emailError = "Please enter an email address."
        )

    private fun processSignInButtonClicked(currentState: LoginViewState): LoginViewState {
        return if (currentState.isLoading) {
            currentState.copy(isLoading = false, emailError = null)
        } else {
            currentState
        }
    }

    private fun processLoginFailed(currentState: LoginViewState) =
        currentState.copy(
            isLoading = false
        )

    private fun processLoginCompleted(currentState: LoginViewState) =
        currentState.copy(
            isLoading = false
        )

    private fun processLoginStarted(currentState: LoginViewState) =
        currentState.copy(
            isLoading = true,
        ).getStateWithoutError()

    private fun processPasswordChanged(
        currentState: LoginViewState,
        action: LoginAction.PasswordChanged
    ) = currentState.copy(
        password = action.newPassword,
    ).getStateWithoutError()

    private fun processEmailChanged(
        currentState: LoginViewState,
        action: LoginAction.EmailChanged
    ) = currentState.copy(
        email = action.newEmail,
    ).getStateWithoutError()
}