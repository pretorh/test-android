package com.pretorh.myapplication.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class SingleHandledEvent<out T>(private val data: T) {
    private val pending = AtomicBoolean(true)

    fun getIfNotHandled(): T? {
        if (pending.getAndSet(false)) {
            return data
        }
        return null
    }
}

inline fun <T> LiveData<SingleHandledEvent<T>>.observeEvent(owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit) {
    // from https://gist.github.com/JoseAlcerreca/e0bba240d9b3cffa258777f12e5c0ae9#gistcomment-2748514
    observe(owner, Observer { event -> event?.getIfNotHandled()?.let(onEventUnhandledContent) })
}
