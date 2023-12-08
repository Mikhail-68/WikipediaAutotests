package org.wikipedia.practice.pageObject

import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers
import org.wikipedia.R
import org.wikipedia.views.PlainPasteEditText

class CreateAccountPage {

    private val usernameInputLayout = R.id.create_account_username
    private val passwordInputLayout = R.id.create_account_password_input

    private val submitBttn = R.id.create_account_submit_button
    private val checkableImageBttn = com.google.android.material.R.id.text_input_end_icon
    private val passwordErrorIcon = com.google.android.material.R.id.text_input_error_icon

    private val passwordErrorTxt = R.string.create_account_password_error



    fun typeUsername(username: String) {
        onView(Matchers.allOf(
            isDescendantOfA(withId(usernameInputLayout)),
            isAssignableFrom(PlainPasteEditText::class.java)
        )).perform(typeText(username))
    }

    fun typePassword(password: String) {
        onView(Matchers.allOf(
            isDescendantOfA(withId(passwordInputLayout)),
            isAssignableFrom(PlainPasteEditText::class.java)
        )).perform(typeText(password))
    }

    fun clickCheckableImagePassword() {
        onView(Matchers.allOf(
            isDescendantOfA(withId(passwordInputLayout)),
            withId(checkableImageBttn)
        )).perform(click())
    }

    fun clickSubmitBttn() {
        onView(withId(submitBttn)).perform(click())
    }


    // CHECKS

    fun checkPasswordIsHide(password: String) {
        val passwordTM = PasswordTransformationMethod()
        onView(withId(passwordInputLayout))
            .check(ViewAssertions.matches(
                hasDescendant(withText(passwordTM.getTransformation(password, null).toString()))
            ))
    }

    fun checkPasswordIsShow(password: String) {
        onView(withId(passwordInputLayout)).check(ViewAssertions.matches(hasDescendant(withText(password))))
    }

    fun checkPasswordErrorTxt() {
        onView(withText(passwordErrorTxt)).check(ViewAssertions.matches(isDisplayed()))
    }

    fun checkErrorPasswordIconIsDisplayed() {
        onView(Matchers.allOf(
            isDescendantOfA(withId(passwordInputLayout)),
            withId(passwordErrorIcon)
        )).check(ViewAssertions.matches(isDisplayed()))
    }

}