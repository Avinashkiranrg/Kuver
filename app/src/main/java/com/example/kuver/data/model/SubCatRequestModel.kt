package com.example.kuver.data.model

import com.squareup.moshi.Json

data class SubCatRequestModel(

    @Json(name = "API-KEY")
    val `API-KEY`: String = "",

    @Json(name = "lang")
    val lang: String = "",

    @Json (name = "category_id")
    val category_id : String = ""
)