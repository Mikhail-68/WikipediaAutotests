package org.wikipedia.practice.pageObject

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.wikipedia.R

class MorePage {

    private val loginBttn = R.id.main_drawer_account_container
    private val settingsBttn = R.id.main_drawer_settings_container

    fun openLoginPage() {
        Espresso.onView(ViewMatchers.withId(loginBttn)).perform(ViewActions.click())
    }

    fun openSettingsPage() {
        Espresso.onView(ViewMatchers.withId(settingsBttn)).perform(ViewActions.click())
    }
}