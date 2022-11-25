package com.linuxias.setup_for_testing.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.linuxias.setup_for_testing.data.local.MarsPhotoItem
import com.linuxias.setup_for_testing.data.remote.MarsPhotoProperty
import com.linuxias.setup_for_testing.other.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeMarsPhotoRepository: MarsPhotoRepository {
    private val marsPhotoItems = mutableListOf<MarsPhotoItem>()

    private val observableMarsPhotoItems = MutableLiveData<List<MarsPhotoItem>>(marsPhotoItems)
    private val observableTotalPrice = MutableLiveData<Int>(0)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun getTotalPrice(): Int {
        return marsPhotoItems.sumOf { it.price }
    }

    private fun refreshLiveData() {
        observableMarsPhotoItems.postValue(marsPhotoItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    override suspend fun insertMarsPhotoItem(item: MarsPhotoItem) {
        marsPhotoItems.add(item)
        refreshLiveData()
    }

    override suspend fun deleteMarsPhotoItem(item: MarsPhotoItem) {
        marsPhotoItems.add(item)
        refreshLiveData()
    }

    override fun observeAllItems(): LiveData<List<MarsPhotoItem>> {
        return observableMarsPhotoItems
    }

    override fun observeTotalPrice(): LiveData<Int> {
        return observableTotalPrice
    }

    override suspend fun searchAllMarsPhotos(): Resource<List<MarsPhotoProperty>> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(emptyList())
        }
    }
}