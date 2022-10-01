package com.linuxias.setup_for_testing.repository

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import com.linuxias.setup_for_testing.data.local.MarsPhotoDao
import com.linuxias.setup_for_testing.data.local.MarsPhotoItem
import com.linuxias.setup_for_testing.data.remote.MarsApiService
import com.linuxias.setup_for_testing.data.remote.MarsPhotoProperty
import com.linuxias.setup_for_testing.other.Resource

import javax.inject.Inject

class MarsPhotoRepositoryImpl @Inject constructor(
    private val marsPhotoDao: MarsPhotoDao,
    private val marsApiService: MarsApiService
)
: MarsPhotoRepository {
    override suspend fun insertMarsPhotoItem(item: MarsPhotoItem) {
        marsPhotoDao.insertMarsPhotoItem(item)
    }

    override suspend fun deleteMarsPhotoItem(item: MarsPhotoItem) {
        marsPhotoDao.deleteMarsPhotoItem(item)
    }

    override fun observeAllItems(): LiveData<List<MarsPhotoItem>> {
        return marsPhotoDao.observeAllMarsPhotoItems()
    }

    override fun observeTotalPrice(): LiveData<Int> {
        return marsPhotoDao.observeTotalPrice()
    }

    override suspend fun searchAllMarsPhotos(): Resource<List<MarsPhotoProperty>> {
        return try {
            val response = marsApiService.getPhotos()
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Log.e("EXCEPTION", "EXCEPTION:", e)
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}