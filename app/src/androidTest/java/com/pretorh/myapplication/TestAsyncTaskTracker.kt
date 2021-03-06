package com.pretorh.myapplication

import android.util.Log
import androidx.test.espresso.IdlingResource
import com.pretorh.myapplication.core.AsyncTaskMethod
import com.pretorh.myapplication.core.AsyncTaskTracker
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class TestAsyncTaskMethod(task: AsyncTaskTracker) : AsyncTaskMethod(task) {
    private val pending = AtomicBoolean(true)

    override fun completedAgain() {
        if (pending.getAndSet(false)) {
            Log.d("AsyncTaskTracker", "async task method completed for first time")
            super.completedAgain()
        }
    }
}

class TestAsyncTaskTracker : AsyncTaskTracker(), IdlingResource {
    private var resourceCallback: IdlingResource.ResourceCallback? = null
    private val pending = AtomicInteger(0)

    override fun start() {
        val pendingCount = pending.incrementAndGet()
        Log.d("AsyncTaskTracker", "started, pending count = $pendingCount")
    }

    override fun startWithMultipleComplete(): AsyncTaskMethod {
        start()
        return TestAsyncTaskMethod(this)
    }

    override fun completed() {
        val done = pending.decrementAndGet() == 0
        Log.d("AsyncTaskTracker", "completed, done=$done")
        if (done) {
            resourceCallback?.onTransitionToIdle()
        }
    }

    override fun getName(): String = "TestAsyncTaskTracker"

    override fun isIdleNow(): Boolean = pending.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }
}
