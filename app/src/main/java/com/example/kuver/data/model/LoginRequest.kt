package com.example.kuver.data.model

import com.squareup.moshi.Json

data class LoginRequest(

    @Json ( name = "API-KEY")
    val `API-KEY`: Int = 0,

    @Json (name = "lang")
    val lang: String = "",

    @Json(name = "phone_number")
    val phone_number: String = "",

    @Json(name = "password")
    val password: String = "",

    @Json(name = "auth_level")
    val auth_level: Int = 0,

    @Json(name = "device_name")
    val device_name: String = "",

    @Json(name = "device_token")
    val device_token: Int = 0,

    )