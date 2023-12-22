package com.graph.apollo.ui.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.graph.apollo.MainActivity
import com.graph.apollo.di.AppModule
import com.graph.apollo.di.ViewModelModule
import com.graph.apollo.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.graph.apollo.R
import com.graph.apollo.utils.Logger


@HiltAndroidTest
@UninstallModules(AppModule::class, ViewModelModule::class)
class CharactersListScreenTest {


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp(){

        hiltRule.inject()

    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun insertQueryAndClickSearchButton_itemsDisplayed() {

            composeRule.onNodeWithTag(TestTags.SEARCH_FIELD).performTextInput("Anything")
            composeRule.onNodeWithContentDescription("Search").performClick()

            composeRule.waitUntilAtLeastOneExists(hasText("1"), 5000)

            composeRule.onNodeWithText("1").assertIsDisplayed()

    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun clickSearchButtonWithSameQuery_errorMessageDisplayed() {

        composeRule.onNodeWithTag(TestTags.SEARCH_FIELD).performTextInput("Anything")
        composeRule.onNodeWithContentDescription("Search").performClick()
        composeRule.onNodeWithContentDescription("Search").performClick()

        val message = composeRule.activity.getString(R.string.error_same_search_query)

        composeRule.waitUntilAtLeastOneExists(hasText(message), 10000)


        composeRule.onNodeWithText(message).assertIsDisplayed()

    }

}