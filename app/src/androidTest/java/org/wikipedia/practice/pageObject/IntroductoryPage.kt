package org.wikipedia.practice.pageObject

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.wikipedia.R

class IntroductoryPage {

    private val skipBttn = R.id.fragment_onboarding_skip_button

    fun skip() {
        Espresso.onView(ViewMatchers.withId(skipBttn)).perform(ViewActions.click())
    }

}