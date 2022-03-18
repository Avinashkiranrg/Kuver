package com.example.kuver.data.model

import com.squareup.moshi.Json

data class CatResponseModel(

    @Json(name = "status")
    val status: String = "",

    @Json(name = "categories")
    val categories: ArrayList<Categories>? = null

)

data class Categories(

    @Json(name = "id")
    val id: String = "",

    @Json(name = "category_name")
    val category_name: String = "",

    @Json(name = "image")
    val image: String = "",

    @Json(name = "status")
    val status: String = ""

)




