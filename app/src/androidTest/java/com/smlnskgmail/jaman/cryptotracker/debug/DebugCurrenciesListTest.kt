package com.smlnskgmail.jaman.cryptotracker.debug

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.cryptotracker.BuildConfig
import com.smlnskgmail.jaman.cryptotracker.MainActivity
import com.smlnskgmail.jaman.cryptotracker.R
import com.smlnskgmail.jaman.cryptotracker.utils.RecyclerViewCountAssertion
import com.smlnskgmail.jaman.cryptotracker.utils.RecyclerViewItemClick
import com.smlnskgmail.jaman.cryptotracker.view.list.recycler.CurrencyHolder
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class DebugCurrenciesListTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    fun loadCurrenciesTest() {
        if (BuildConfig.API_IMPL != "DEBUG") {
            fail(
                "You must run this test in DEBUG API_IMPL!"
            )
        }

        sleep(10_000)
        onView(withId(R.id.currencies_list)).check(
            RecyclerViewCountAssertion(10)
        )

        onView(withId(R.id.currencies_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CurrencyHolder>(
                0,
                RecyclerViewItemClick(R.id.currency_item)
            )
        )
    }

}