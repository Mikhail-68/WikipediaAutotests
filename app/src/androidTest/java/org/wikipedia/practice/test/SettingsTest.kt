package org.wikipedia.practice.test

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import org.junit.Test


import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Rule
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity

import org.junit.Before
import org.wikipedia.practice.pageObject.ExploreFeedPage
import org.wikipedia.practice.pageObject.IntroductoryPage
import org.wikipedia.practice.pageObject.MainPage
import org.wikipedia.practice.pageObject.MorePage
import org.wikipedia.practice.pageObject.SettingsPage
import org.wikipedia.settings.Prefs


@RunWith(AndroidJUnit4::class)
class SettingsTest {

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
    fun allCheckboxIsOn() {
        MainPage().openMorePage()
        MorePage().openSettingsPage()
        SettingsPage().openExploreFeed()

        ExploreFeedPage().checkAllCheckboxIsOn()
    }

    @Test
    fun aboutApplicationWikipedia() {
        MainPage().openMorePage()
        MorePage().openSettingsPage()

        SettingsPage().openAboutDescriptionPage()

        with(SettingsPage()) {
            checkContributorsHeadingIsEnabled()
            checkTranslatorsHeadingIsEnabled()
            checkAppLicenseHeadingIsEnabled()
        }
    }

    @Test
    fun checkOpenWindowToBrowser() {
        MainPage().openMorePage()
        MorePage().openSettingsPage()

        Intents.intending(hasAction(Intent.ACTION_VIEW)).respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
        SettingsPage().openPrivacyPolicy()
        Intents.intended(hasAction(Intent.ACTION_VIEW))
    }

}