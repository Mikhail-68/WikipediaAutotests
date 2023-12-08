package org.wikipedia.practice

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import org.junit.Test


import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Rule
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity

import org.junit.Before
import org.wikipedia.R
import org.wikipedia.onboarding.InitialOnboardingActivity
import org.wikipedia.practice.pageObject.CreateAccountPage
import org.wikipedia.practice.pageObject.ExploreFeedPage
import org.wikipedia.practice.pageObject.IntroductoryPage
import org.wikipedia.practice.pageObject.MainPage
import org.wikipedia.practice.pageObject.MorePage
import org.wikipedia.practice.pageObject.SettingsPage
import org.wikipedia.settings.Prefs


@RunWith(AndroidJUnit4::class)
class MyTest {

    private val introductoryPage = IntroductoryPage()
    private val mainPage = MainPage()
    private val morePage = MorePage()
    private val settingsPage = SettingsPage()

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

//    @get:Rule
//    val mainActivity = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val intentsTestRule = IntentsRule()

    @Before
    fun before() {
        introductoryPage.skip()
    }

    @After
    fun after() {
        Prefs.isInitialOnboardingEnabled = true
    }

    @Test
    fun allCheckboxIsOn() {
        mainPage.openMorePage()
        morePage.openSettingsPage()
        settingsPage.openExploreFeed()

        ExploreFeedPage().checkAllCheckboxIsOn()

//        mainActivity.scenario.onActivity { activityScenarioRule ->
//            val recyclerView = activityScenarioRule.findViewById<RecyclerView>(R.id.content_types_recycler)
//            val itemCount = recyclerView.adapter
//            println(itemCount)
//        }

    }

    @Test
    fun aboutApplicationWikipedia() {
        mainPage.openMorePage()
        morePage.openSettingsPage()

        settingsPage.openAboutDescriptionPage()

        with(settingsPage) {
            checkContributorsHeadingIsEnabled()
            checkTranslatorsHeadingIsEnabled()
            checkAppLicenseHeadingIsEnabled()
        }
    }

    @Test
    fun checkOpenWindowToBrowser() {
        mainPage.openMorePage()
        morePage.openSettingsPage()

        val expectedResult = hasAction(Intent.ACTION_VIEW)

        Intents.intending(expectedResult).respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
        settingsPage.openPrivacyPolicy()
        Intents.intended(expectedResult)
    }

    @Test
    fun checkPasswordHidden() {
        val createAccountPage = CreateAccountPage()

        val username = "UserMyUser"
        val password = "12dy345_abcde"

        mainPage.openMorePage()
        morePage.openLoginPage()

        with(createAccountPage) {
            typeUsername(username)
            typePassword(password)

            checkPasswordIsHide(password)
            clickCheckableImagePassword()
            checkPasswordIsShow(password)
        }
    }

    @Test
    fun checkValidatedPasswordField() {
        val createAccountPage = CreateAccountPage()

        val username = "UserMyUser"
        val password = "qwerty"

        mainPage.openMorePage()
        morePage.openLoginPage()

        createAccountPage.typeUsername(username)
        createAccountPage.typePassword(password)

        createAccountPage.clickSubmitBttn()

        createAccountPage.checkPasswordErrorTxt()
        createAccountPage.checkErrorPasswordIconIsDisplayed()
    }
}