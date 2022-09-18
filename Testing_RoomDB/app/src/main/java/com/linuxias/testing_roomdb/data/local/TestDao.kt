package com.linuxias.testing_roomdb.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TestDao {
    @Query("SELECT * FROM TestEntity")
    fun getAll() : LiveData<List<TestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(testEntity: TestEntity)

    @Delete
    suspend fun deleteEntity(testEntity: TestEntity)
}