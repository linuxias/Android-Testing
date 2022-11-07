package com.linuxias.setup_for_testing.di

import android.content.Context
import androidx.room.Room
import com.linuxias.setup_for_testing.data.local.MarsPhotoDao
import com.linuxias.setup_for_testing.data.local.MarsPhotoItemDatabase
import com.linuxias.setup_for_testing.data.remote.MARS_BASE_URL
import com.linuxias.setup_for_testing.data.remote.MarsApiService
import com.linuxias.setup_for_testing.repository.MarsPhotoRepository
import com.linuxias.setup_for_testing.repository.MarsPhotoRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMarsPhotoRepository (
        dao: MarsPhotoDao,
        service: MarsApiService
    ) = MarsPhotoRepositoryImpl(dao, service) as MarsPhotoRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceeModule {
    @Singleton
    @Provides
    fun provideMarsPhotoDao(
        database: MarsPhotoItemDatabase
    ) = database.marsPhotoDao()

    @Singleton
    @Provides
    fun provideMarsApiService(): MarsApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(MARS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MarsApiService::class.java)
    }
}
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideMarsPhotoItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MarsPhotoItemDatabase::class.java, DATABASE_NAME).build()
}

val DATABASE_NAME = "mars_photo_db"