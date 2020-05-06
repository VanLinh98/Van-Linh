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

    @Rule //quy tắc test JUnit4
    @JvmField //chỉ thị trình biên dịch không tạo ra các getters và setters cho thuộc tính
    /*khai báo cho Espresso biết Activity nào để mở hoặc khởi động trước
     khi thực hiện và phá hủy sau khi thực hiện bất kỳ phương thức test nào.*/
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java)

    @Test
    fun view_dislay()
    {
        onView(withId(R.id.MenuSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_history)).check(matches(isDisplayed()))
    }
    @Test
    fun  clickItem()
    {
        onView(withId(R.id.rv_history)).perform(
                RecyclerViewActions.actionOnItemAtPosition<AdapterHistory.ViewHolder>(3, click()))

    }
    @Test
    @Throws(Exception::class)
    fun search()
    {
        val cityhistory = "Ho Chi Minh City"
        val city = "Khanh Hoa"
        onView(withId(R.id.MenuSearch)).perform(SearchViewActionExtension.newText(cityhistory), closeSoftKeyboard())
        onView(withId(R.id.rv_history)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.pressBack()
        onView(withId(R.id.MenuSearch)).perform(SearchViewActionExtension.submitText(city), closeSoftKeyboard())
        onView(withId(R.id.rv_history)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.pressBack()
    }

}