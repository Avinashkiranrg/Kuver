package com.example.kuver.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kuver.local.entity.CatDBModel

@Dao
interface CatDao {

    @Query("SELECT * FROM CatDBModel")
    suspend fun getAll(): List<CatDBModel>

    @Insert
    suspend fun insertAll(category: List<CatDBModel>)

    @Delete
    suspend fun deleteAll(catDBModel: CatDBModel)

}