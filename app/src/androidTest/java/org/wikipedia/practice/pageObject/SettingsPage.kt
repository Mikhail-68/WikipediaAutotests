package org.wikipedia.practice.pageObject

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions

import org.wikipedia.R

class SettingsPage {

    private val recyclerView = withId(R.id.recycler_view)

    fun openAboutDescriptionPage() {
        val aboutDestriptionTxt = R.string.about_description

        onView(recyclerView).perform(
            RecyclerViewActions.actionOnItem<ViewHolder>(
                hasDescendant(withText(aboutDestriptionTxt)),
                click()
            )
        )
    }

    fun openPrivacyPolicy() {
        val privacyPolicyDescription = R.string.privacy_policy_description

        onView(recyclerView).perform(RecyclerViewActions.actionOnItem<ViewHolder>(
            hasDescendant(withText(privacyPolicyDescription)),
            click()
        ))
    }

    fun openExploreFeed() {
        onView(withText(R.string.preference_title_customize_explore_feed)).perform(click())
    }



    // CHECKS

    fun checkContributorsHeadingIsEnabled() {
        onView(withId(R.id.about_contributors)).check(matches(isEnabled()))
    }

    fun checkTranslatorsHeadingIsEnabled() {
        onView(withId(R.id.about_translators)).check(matches(isEnabled()))
    }

    fun checkAppLicenseHeadingIsEnabled() {
        onView(withId(R.id.about_app_license)).check(matches(isEnabled()))
    }
}