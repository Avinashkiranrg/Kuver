package com.example.kuver.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatDBModel(


    @PrimaryKey val id: String,
    @ColumnInfo(name = "category_name") val category_name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "status") val status: String,

    )