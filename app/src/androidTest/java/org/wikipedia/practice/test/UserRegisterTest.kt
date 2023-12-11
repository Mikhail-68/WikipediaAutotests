package org.wikipedia.practice.test

import org.junit.Test

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Rule
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity

import org.junit.Before
import org.wikipedia.practice.pageObject.CreateAccountPage
import org.wikipedia.practice.pageObject.IntroductoryPage
import org.wikipedia.practice.pageObject.MainPage
import org.wikipedia.practice.pageObject.MorePage
import org.wikipedia.settings.Prefs


@RunWith(AndroidJUnit4::class)
class UserRegisterTest {

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val intentsTestRule = IntentsRule()

    @Before
    fun before() {
        IntroductoryPage().skip()
    }

    @After
    fun after() {
        Prefs.isInitialOnboardingEnabled = true
    }

    @Test
    fun checkPasswordHidden() {
        val username = "UserMyUser"
        val password = "12dy345_abcde"

        MainPage().openMorePage()
        MorePage().openLoginPage()

        with(CreateAccountPage()) {
            typeUsername(username)
            typePassword(password)

            checkPasswordIsHide(password)
            clickCheckableImagePassword()
            checkPasswordIsShow(password)
        }
    }

    @Test
    fun checkValidatedPasswordField() {
        val username = "UserMyUser"
        val password = "qwerty"

        MainPage().openMorePage()
        MorePage().openLoginPage()

        with(CreateAccountPage()) {
            typeUsername(username)
            typePassword(password)

            clickSubmitBttn()

            checkPasswordErrorTxt()
            checkErrorPasswordIconIsDisplayed()
        }
    }
}