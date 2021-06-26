package com.example.mviapp.redux

interface IMiddleware<S: IState, A: IAction> {
    suspend fun process(
        action: A,
        currentState: S,
        store: Store<S, A>,
    )
}