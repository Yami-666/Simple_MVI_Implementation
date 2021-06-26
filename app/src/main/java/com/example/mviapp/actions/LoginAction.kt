package com.example.mviapp.actions

import com.example.mviapp.redux.IAction

sealed class LoginAction : IAction {
    data class EmailChanged(val newEmail: String) : LoginAction()
    data class PasswordChanged(val newPassword: String) : LoginAction()
    object SignInButtonClicked : LoginAction()
    object LoginStarted: LoginAction()
    object LoginCompleted: LoginAction()
    data class LoginFailed(val error: Throwable?): LoginAction()
    object InvalidEmailSubmitted : LoginAction()
}