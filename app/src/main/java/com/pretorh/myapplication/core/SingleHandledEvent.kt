package com.pretorh.myapplication.core

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
