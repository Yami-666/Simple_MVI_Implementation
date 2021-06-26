package com.example.mviapp.extensions

import android.view.View
import android.widget.ProgressBar
import com.example.mviapp.redux.State

fun ProgressBar.setVisible(viewState: State) {
    this.visibility = if (viewState.showProgressBar) View.VISIBLE else View.GONE
}