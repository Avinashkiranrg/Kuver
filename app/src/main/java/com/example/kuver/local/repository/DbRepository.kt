package com.example.kuver.local.repository

import com.example.kuver.local.DatabaseHelper
import com.example.kuver.local.entity.CatDBModel

class DbRepository(private val databaseHelper: DatabaseHelper) {

    suspend fun getAll() = databaseHelper.getCat()
    suspend fun insertAll(catDBModel: List<CatDBModel>) = databaseHelper.insertAll(catDBModel)
}