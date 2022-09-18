package com.linuxias.testing_roomdb.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.linuxias.testing_roomdb.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TestDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TestDatabase
    private lateinit var dao: TestDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.testDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTestEntity() = runTest {
        val testEntity = TestEntity(1, "Title", "Content", 1, "2022-09-30")
        dao.insertEntity(testEntity)

        val allTestEntities = dao.getAll().getOrAwaitValue()
        assertThat(allTestEntities).contains(testEntity)
    }

    @Test
    fun deleteTestEntity() = runTest {
        val testEntity = TestEntity(1, "Title", "Content", 1, "2022-09-30")
        dao.insertEntity(testEntity)
        dao.deleteEntity(testEntity)

        val allTestEntities = dao.getAll().getOrAwaitValue()
        assertThat(allTestEntities).doesNotContain(testEntity)
    }

    @Test
    fun getAllTest() = runTest {
        val testEntity1 = TestEntity(1, "Title1", "Content1", 1, "2022-09-30")
        val testEntity2 = TestEntity(2, "Title2", "Content2", 2, "2022-10-30")
        val testEntity3 = TestEntity(3, "Title3", "Content3", 3, "2022-11-30")

        dao.insertEntity(testEntity1)
        dao.insertEntity(testEntity2)
        dao.insertEntity(testEntity3)

        val allTestEntities = dao.getAll().getOrAwaitValue()
        assertThat(allTestEntities).contains(testEntity1)
        assertThat(allTestEntities).contains(testEntity2)
        assertThat(allTestEntities).contains(testEntity3)
    }
}