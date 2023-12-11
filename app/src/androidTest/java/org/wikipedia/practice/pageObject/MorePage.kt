package org.wikipedia.practice.pageObject

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.wikipedia.R

class MorePage {

    private val loginBttn = ViewMatchers.withId(R.id.main_drawer_account_container)
    private val settingsBttn = ViewMatchers.withId(R.id.main_drawer_settings_container)

    fun openLoginPage() {
        Espresso.onView(loginBttn).perform(ViewActions.click())
    }

    fun openSettingsPage() {
        Espresso.onView(settingsBttn).perform(ViewActions.click())
    }
}