package com.pretorh.myapplication

import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import java.io.File

class CustomScreenCaptureProcessor : BasicScreenCaptureProcessor() {
    init {
        // based https://stackoverflow.com/a/49009120/1016377
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val externalFilesDir = targetContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        mDefaultScreenshotPath = File(externalFilesDir, "screenshots")
    }
}
