package com.linuxias.setup_for_testing.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MarsPhotoItem::class],
    version = 1
)
abstract class MarsPhotoItemDatabase : RoomDatabase() {
    abstract fun marsPhotoDao(): MarsPhotoDao
}