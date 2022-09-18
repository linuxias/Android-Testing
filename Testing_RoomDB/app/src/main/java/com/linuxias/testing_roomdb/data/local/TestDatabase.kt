package com.linuxias.testing_roomdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TestEntity::class],
    version = 1
)
abstract class TestDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
}