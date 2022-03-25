package com.example.kuver.local

import com.example.kuver.local.entity.CatDBModel

interface DatabaseHelper {

    suspend fun getCat(): List<CatDBModel>

    suspend fun insertAll(catagory: List<CatDBModel>)
}