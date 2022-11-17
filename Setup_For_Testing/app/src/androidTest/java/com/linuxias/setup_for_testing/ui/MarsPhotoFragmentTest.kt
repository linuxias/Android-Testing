package com.linuxias.setup_for_testing.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.linuxias.setup_for_testing.launchFragmentInHiltContainer
import com.linuxias.setup_for_testing.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MarsPhotoFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickAddMarsPhotoItemButton_navigateToAddMarshPhotoItemFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<MarsPhotoFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        //Click Floating action button
        onView(withId(R.id.fabAddMarsPhotoItem)).perform(click())

        Mockito.verify(navController).navigate(
            R.id.action_marsPhotoFragment_to_addMarsPhotoFragment
        )
    }
}