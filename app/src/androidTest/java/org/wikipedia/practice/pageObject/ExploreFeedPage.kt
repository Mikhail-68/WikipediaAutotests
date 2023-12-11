package org.wikipedia.practice.pageObject

import android.view.View
import androidx.core.util.Preconditions
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Description
import org.hamcrest.Matchers
import org.wikipedia.R

class ExploreFeedPage {

    private val recyclerView = withId(R.id.content_types_recycler)

    fun checkAllCheckboxIsOn() {
        val countCheckboxes = 7

        for(pos in 0 until countCheckboxes) {
            onView(recyclerView)
                .perform(RecyclerViewActions.scrollToPosition<ViewHolder>(pos))
                .check(matches(atPosition(pos, Matchers.allOf(hasDescendant(isChecked())))))
        }
    }

    fun atPosition(
        position: Int,
        itemMatcher: org.hamcrest.Matcher<View>
    ): org.hamcrest.Matcher<View?>? {
        Preconditions.checkNotNull(itemMatcher)
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}