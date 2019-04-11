package com.pretorh.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Test

class SimpleActivityTest {
    private val activity = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Test
    fun isLaunchable() {
        activity.launchActivity(null)
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }
}
