package com.example.kuver.local

import com.example.kuver.local.entity.CatDBModel

class DatabaseHelperImpl(private val appDataBase: AppDataBase) : DatabaseHelper {


    override suspend fun getCat(): List<CatDBModel> {
        return appDataBase.catDao().getAll()
    }

    override suspend fun insertAll(catagory: List<CatDBModel>) {
        return appDataBase.catDao().insertAll(catagory)
    }

}