package com.example.kuver.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kuver.local.dao.CatDao
import com.example.kuver.local.entity.CatDBModel

@Database(entities = [CatDBModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun catDao() : CatDao
}