package com.linuxias.setup_for_testing.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linuxias.setup_for_testing.data.local.MarsPhotoItem
import com.linuxias.setup_for_testing.data.remote.MarsPhotoProperty
import com.linuxias.setup_for_testing.other.Event
import com.linuxias.setup_for_testing.repository.MarsPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsPhotoViewModel @Inject constructor(
    private val repository: MarsPhotoRepository
) : ViewModel() {

    val marsPhotoItem = repository.observeAllItems()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<MarsPhotoProperty>>()
    val images: LiveData<Event<MarsPhotoProperty>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertMarsPhotoItemStatus = MutableLiveData<Event<MarsPhotoItem>>()
    val insertMarsPhotoItemStatus: LiveData<Event<MarsPhotoItem>> = _insertMarsPhotoItemStatus

    suspend fun setCurImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun insertMarsPhotoItemIntoDb(marsPhotoItem: MarsPhotoItem) = viewModelScope.launch {
        repository.insertMarsPhotoItem(marsPhotoItem)
    }

    fun deleteMarsPhotoItemIntoDb(marsPhotoItem: MarsPhotoItem) = viewModelScope.launch {
        repository.deleteMarsPhotoItem(marsPhotoItem)
    }

    fun insertMarsPhotoItem(id: String, price: Int, type: String) {

    }

    fun searchForImage(imageQuery: String) {

    }
}