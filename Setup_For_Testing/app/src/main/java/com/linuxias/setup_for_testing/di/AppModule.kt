package com.linuxias.setup_for_testing.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.linuxias.setup_for_testing.R
import com.linuxias.setup_for_testing.adapters.ImageAdapter
import com.linuxias.setup_for_testing.data.local.MarsPhotoDao
import com.linuxias.setup_for_testing.data.local.MarsPhotoItemDatabase
import com.linuxias.setup_for_testing.data.remote.MARS_BASE_URL
import com.linuxias.setup_for_testing.data.remote.MarsApiService
import com.linuxias.setup_for_testing.repository.MarsPhotoRepository
import com.linuxias.setup_for_testing.repository.MarsPhotoRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
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
object GlideModule {
    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )
}

@Module
@InstallIn(SingletonComponent::class)
object ImageAdapterModule {
    @Singleton
    @Provides
    fun provideImageAdapter(
        glide: RequestManager
    ) = ImageAdapter(glide)
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