package com.example.kuver.data.model

import com.squareup.moshi.Json

data class HomeRequestModel(

    @Json(name = "API-KEY")
    val `API-KEY`: String = "",

    @Json(name = "lang")
    val lang: String = "",

    @Json(name = "category_id")
    val category_id: String = "",

    @Json(name = "sub_category_id")
    val sub_category_id: String = "",

    @Json(name = "post_type")
    val post_type: String = "",

    @Json(name = "city_id")
    val city_id: String = "",

    @Json(name = "latitude")
    val latitude: String = "",

    @Json(name = "longitude")
    val longitude: String = ""


)