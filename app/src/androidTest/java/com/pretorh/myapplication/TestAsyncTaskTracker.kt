package com.pretorh.myapplication

import android.util.Log
import com.pretorh.myapplication.core.AsyncTaskTracker
import java.util.concurrent.atomic.AtomicInteger

class TestAsyncTaskTracker : AsyncTaskTracker() {
    private val pending = AtomicInteger(0)

    override fun start() {
        val pendingCount = pending.incrementAndGet()
        Log.d("AsyncTaskTracker", "started, pending count = $pendingCount")
    }

    override fun completed() {
        val done = pending.decrementAndGet() == 0
        Log.d("AsyncTaskTracker", "completed, done=$done")
    }
}
