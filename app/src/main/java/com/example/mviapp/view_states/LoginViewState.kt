package com.example.mviapp.view_states

import com.example.mviapp.redux.State

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    override val showProgressBar: Boolean = false
) : State