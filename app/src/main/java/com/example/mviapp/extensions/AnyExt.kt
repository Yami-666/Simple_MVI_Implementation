package com.example.mviapp.extensions

fun <T> T?.orIfNull(doFunc: () -> T) {
    if (this == null) {
        doFunc.invoke()
    }
}

inline fun <T> T.takeIfTrue(predicate: (T) -> Boolean): Boolean? {
    return if (predicate(this)) true else null
}

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun Boolean?.orTrue(): Boolean {
    return this ?: true
}