package com.pretorh.myapplication

import android.graphics.Bitmap
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.screenshot.ScreenCaptureProcessor
import androidx.test.runner.screenshot.Screenshot
import org.junit.Test
import java.util.*

class SimpleActivityTest {
    private val activity = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun isLaunchable() {
        activity.launchActivity(null)
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun canTakeScreenshots() {
        activity.launchActivity(null)

        val capture = Screenshot.capture(activity.activity).apply {
            name = "screenshot${Date().time}"
            format = Bitmap.CompressFormat.PNG
        }

        val set = hashSetOf<ScreenCaptureProcessor>(CustomScreenCaptureProcessor())
        capture.process(set)
    }
}
