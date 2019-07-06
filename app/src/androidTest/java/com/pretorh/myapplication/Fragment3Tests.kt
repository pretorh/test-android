package com.pretorh.myapplication

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.junit.Test

class Fragment3Tests {
    private val activity = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun canLaunchFragment() {
        launchToMainFragment()
        navigateToFragment3()
        assertTextIsChanged()
    }

    private fun launchToMainFragment() {
        activity.launchActivity(null)
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun navigateToFragment3() {
        Espresso.onView(ViewMatchers.withId(R.id.button_navigate3))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun assertTextIsChanged() {
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.text3), ViewMatchers.withText("hello from Fragment3ViewModel")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.text3_2), ViewMatchers.withText("hello from Fragment3ViewModel.getText2 (0)")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
