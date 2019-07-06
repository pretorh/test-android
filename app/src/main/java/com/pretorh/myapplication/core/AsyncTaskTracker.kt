package com.pretorh.myapplication.core

open class AsyncTaskMethod(private val task: AsyncTaskTracker) {
    open fun completedAgain() {
        task.completed()
    }
}

open class AsyncTaskTracker {
    open fun start() = Unit
    open fun startWithMultipleComplete() = AsyncTaskMethod(this)
    open fun completed() = Unit
}
