package com.example.mviapp

import android.util.Log
import com.example.mviapp.actions.LoginAction
import com.example.mviapp.extensions.orFalse
import com.example.mviapp.extensions.takeIfTrue
import com.example.mviapp.redux.IMiddleware
import com.example.mviapp.redux.Store
import com.example.mviapp.repository.ILoggingRepository
import com.example.mviapp.view_states.LoginViewState

class LoginNetworkMiddleware(
    private val loginRepository: ILoggingRepository
) : IMiddleware<LoginViewState, LoginAction> {
    override suspend fun process(
        action: LoginAction,
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ) {
        when (action) {
            is LoginAction.SignInButtonClicked -> {
                if (currentState.email.isEmpty()) {
                    store.dispatch(LoginAction.InvalidEmailSubmitted)
                    return
                }

                store.dispatch(LoginAction.LoginStarted)

                val isSuccessful = loginRepository.login(
                    email = currentState.email,
                    password = currentState.password
                )

                checkLoginResult(isSuccessful, store)
            }
            else -> {
                Log.v("LoginNetworkMiddleware", "process(): $action has been skipped!")
            }
        }
    }

    private suspend fun checkLoginResult(
        isSuccessful: Boolean,
        store: Store<LoginViewState, LoginAction>
    ) {
        if (isSuccessful) {
            store.dispatch(LoginAction.LoginCompleted)
        } else {
            store.dispatch(LoginAction.LoginFailed(null))
        }
    }
}