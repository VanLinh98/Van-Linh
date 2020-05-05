package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.Adapter.AdapterHistory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java)

    @Test
    fun view_dislay()
    {
        onView(withId(R.id.MenuSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.tvhistory)).check(matches(isDisplayed())).check(matches(withText("History Search")))
        onView(withId(R.id.rv_history)).check(matches(isDisplayed()))
    }
    @Test
    fun  clickItem()
    {
        onView(withId(R.id.rv_history)).perform(RecyclerViewActions.actionOnItemAtPosition<AdapterHistory.ViewHolder>(1, click()))
    }
    @Test
    fun search()
    {
        onView(withId(R.id.MenuSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.tvhistory)).check(matches(isDisplayed())).check(matches(withText("History Search")))
        onView(withId(R.id.rv_history)).check(matches(isDisplayed()))
        onView(withId(R.id.MenuSearch)).perform(SearchViewActionExtension.typeText("sin"), closeSoftKeyboard())
        onView(withId(R.id.MenuSearch)).perform(click())
        onView(withId(R.id.MenuSearch)).perform(SearchViewActionExtension.submitText("sin"), closeSoftKeyboard())
        onView(withId(R.id.MenuSearch)).perform(click())
        onView(withId(R.id.rv_history)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.pressBack()
    }

}