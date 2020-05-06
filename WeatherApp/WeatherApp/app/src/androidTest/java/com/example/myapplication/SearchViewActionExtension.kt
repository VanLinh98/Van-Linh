package com.example.myapplication

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import java.lang.Exception

class SearchViewActionExtension {
    companion object {
        fun submitText(text: String): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
                }
                override fun getDescription(): String {
                    return "Exception"
                }
                override fun perform(uiController: UiController, view: View) {
                    (view as SearchView).setQuery(text, true) //submit=true will fire search
                }
            }
        }
        fun newText(text: String): ViewAction {
            return object : ViewAction {
                override fun getDescription(): String {
                    return "Exception"
                }

                override fun getConstraints(): Matcher<View> {
                    return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
                }
                //THỰC THI
                override fun perform(uiController: UiController?, view: View?) {
                    (view as SearchView).setQuery(text, false)
                }
            }
        }
    }
}