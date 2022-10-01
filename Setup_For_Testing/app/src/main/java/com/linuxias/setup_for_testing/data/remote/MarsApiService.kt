package com.linuxias.setup_for_testing.data.remote

import retrofit2.Response
import retrofit2.http.GET

const val MARS_BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com/"

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): Response<List<MarsPhotoProperty>>
}