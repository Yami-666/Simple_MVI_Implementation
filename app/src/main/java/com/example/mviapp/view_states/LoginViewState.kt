package com.example.mviapp.view_states

import com.example.mviapp.redux.ILoadingState

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    override val isLoading: Boolean = false
) : ILoadingState {
    companion object {
        fun LoginViewState.getStateWithoutError() =
            this.copy(emailError = null, passwordError = null)
    }
}