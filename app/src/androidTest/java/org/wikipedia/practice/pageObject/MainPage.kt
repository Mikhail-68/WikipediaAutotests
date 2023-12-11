package org.wikipedia.practice.pageObject

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.wikipedia.R

class MainPage {
    private val moreBttn = ViewMatchers.withId(R.id.nav_more_container)

    fun openMorePage() {
        Espresso.onView(moreBttn).perform(ViewActions.click())
    }

}