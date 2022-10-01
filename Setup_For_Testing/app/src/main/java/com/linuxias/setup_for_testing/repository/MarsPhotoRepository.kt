package com.linuxias.setup_for_testing.repository

import androidx.lifecycle.LiveData
import com.linuxias.setup_for_testing.data.local.MarsPhotoItem
import com.linuxias.setup_for_testing.data.remote.MarsPhotoProperty
import com.linuxias.setup_for_testing.other.Resource

interface MarsPhotoRepository {
    suspend fun insertMarsPhotoItem(item: MarsPhotoItem)

    suspend fun deleteMarsPhotoItem(item: MarsPhotoItem)

    fun observeAllItems(): LiveData<List<MarsPhotoItem>>

    fun observeTotalPrice(): LiveData<Int>

    suspend fun searchAllMarsPhotos(): Resource<List<MarsPhotoProperty>>
}