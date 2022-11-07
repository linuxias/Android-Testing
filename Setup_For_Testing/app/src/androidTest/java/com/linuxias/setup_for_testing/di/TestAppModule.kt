package com.linuxias.setup_for_testing.di

import android.content.Context
import androidx.room.Room
import com.linuxias.setup_for_testing.data.local.MarsPhotoItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {
    @Provides
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, MarsPhotoItemDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}