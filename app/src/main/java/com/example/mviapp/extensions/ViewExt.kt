package com.example.mviapp.extensions

import android.view.View
import android.widget.ProgressBar
import com.example.mviapp.redux.ILoadingState

fun ProgressBar.setVisible(viewLoadingState: ILoadingState) {
    this.visibility = if (viewLoadingState.isLoading) View.VISIBLE else View.GONE
}