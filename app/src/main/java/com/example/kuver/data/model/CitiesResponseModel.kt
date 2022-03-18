package com.example.kuver.data.model

import com.squareup.moshi.Json

data class CitiesResponseModel(

    @Json(name = "status")
    val status: String = "",

    @Json(name = "cities")
    val cities: ArrayList<Cities>? = null

)

data class Cities(

    @Json(name = "id")
    val id: String = "",

    @Json(name = "city_name")
    val city_name: String = "",

    @Json(name = "status")
    val status: String = ""

)




