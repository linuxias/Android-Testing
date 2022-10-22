package com.linuxias.setup_for_testing.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.linuxias.setup_for_testing.getOrAwaitValueTest
import com.linuxias.setup_for_testing.other.Status
import com.linuxias.setup_for_testing.repository.FakeMarsPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MarsPhotoViewModelTest {
    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MarsPhotoViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = MarsPhotoViewModel(FakeMarsPhotoRepository())
    }

    @Test
    fun `insert mars photo item with empty id field, returns error`() {
        viewModel.insertMarsPhotoItem("", 3500, "buy")
        val value = viewModel.insertMarsPhotoItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert mars photo item with negative price field, returns error`() {
        viewModel.insertMarsPhotoItem("10000", -40, "buy")
        val value = viewModel.insertMarsPhotoItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert mars photo item with negative type field, returns error`() {
        viewModel.insertMarsPhotoItem("10000", 100, "")
        val value = viewModel.insertMarsPhotoItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert mars photo item with valid input, returns success`() {
        viewModel.insertMarsPhotoItem("10000", 100, "buy")
        val value = viewModel.insertMarsPhotoItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `total price is correct`() {
        viewModel.insertMarsPhotoItem("10000", 100, "buy")
        viewModel.insertMarsPhotoItem("10001", 200, "buy")
        viewModel.insertMarsPhotoItem("10002", 300, "buy")
        val value = viewModel.totalPrice.getOrAwaitValueTest()
        assertThat(value).isEqualTo(600)
    }
}