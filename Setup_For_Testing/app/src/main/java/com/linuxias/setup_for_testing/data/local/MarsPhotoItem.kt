package com.linuxias.setup_for_testing.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mars_photo_items")
data class MarsPhotoItem(
    var price: Int,
    var id: String,
    var type: String,
    var img_src: String,
    @PrimaryKey(autoGenerate=true)
    val number: Int? = null
)
