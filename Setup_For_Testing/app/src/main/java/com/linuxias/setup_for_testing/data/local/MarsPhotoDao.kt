package com.linuxias.setup_for_testing.data.local;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface MarsPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarsPhotoItem(marsPhotoItem: MarsPhotoItem)

    @Delete
    suspend fun deleteMarsPhotoItem(marsPhotoItem: MarsPhotoItem)

    @Query("SELECT * FROM mars_photo_items")
    fun observeAllMarsPhotoItems(): LiveData<List<MarsPhotoItem>>

    @Query("SELECT SUM(price) FROM mars_photo_items")
    fun observeTotalPrice(): LiveData<Int>
}
