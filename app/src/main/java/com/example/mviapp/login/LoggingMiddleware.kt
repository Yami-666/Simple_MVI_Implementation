package com.example.mviapp.login

import com.example.mviapp.redux.IAction
import com.example.mviapp.redux.IMiddleware
import com.example.mviapp.redux.IState
import com.example.mviapp.redux.Store

class LoggingMiddleware<S : IState, A : IAction> : IMiddleware<S, A> {
    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {}
}