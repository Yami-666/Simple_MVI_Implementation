package com.example.mviapp.redux

interface IReducer<S: IState, A: IAction> {
    fun reduce(currentState: S, action: A): S
}