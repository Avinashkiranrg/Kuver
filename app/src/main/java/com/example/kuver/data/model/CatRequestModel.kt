package com.example.kuver.data.model

import com.squareup.moshi.Json

data class CatRequestModel(

    @Json(name = "API-KEY")
    val `API-KEY`: String = "",

    @Json(name = "lang")
    val lang: String = ""
)