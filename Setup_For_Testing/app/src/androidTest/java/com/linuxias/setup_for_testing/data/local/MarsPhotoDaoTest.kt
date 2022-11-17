package com.linuxias.setup_for_testing.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.linuxias.setup_for_testing.getOrAwaitValue
import com.linuxias.setup_for_testing.launchFragmentInHiltContainer
import com.linuxias.setup_for_testing.ui.AddMarsPhotoItemFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MarsPhotoDaoTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: MarsPhotoItemDatabase
    private lateinit var dao: MarsPhotoDao

    @Before
    fun setup() {
        /*
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MarsPhotoItemDatabase::class.java
        ).allowMainThreadQueries().build()
        */
        hiltRule.inject()
        dao = database.marsPhotoDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTestEntity() = runTest {
        val testEntity = MarsPhotoItem(1000, "id", "type", "resource_link", 1)
        dao.insertMarsPhotoItem(testEntity)

        val allTestEntities = dao.observeAllMarsPhotoItems().getOrAwaitValue()
        assertThat(allTestEntities).contains(testEntity)
    }
}