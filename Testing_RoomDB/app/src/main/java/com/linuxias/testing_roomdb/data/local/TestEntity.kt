package com.linuxias.testing_roomdb.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TestEntity")
data class TestEntity (
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name="title") val title : String,
    @ColumnInfo(name="content") val content : String,
    @ColumnInfo(name="importance") val importance : Int,
    @ColumnInfo(name="due") val due : String
)
