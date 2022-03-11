package com.example.kuver.data.model

import com.squareup.moshi.Json

data class RegistrationRequestModel(

    @Json ( name = "API-KEY")
    val `API-KEY`: Int = 0,

    @Json (name = "lang")
    val lang: String = "",

    @Json(name = "full_name")
    val full_name: String = "",

    @Json(name = "username")
    val username: String = "",

    @Json(name = "email")
    val email: String = "",

    @Json(name = "country_code")
    val country_code: String = "",

    @Json(name = "phone")
    val phone: String = "",

    @Json ( name = "password")
    val password: String = "",

    @Json (name = "confirm_password")
    val confirm_password: String = "",

    @Json(name = "agree_terms")
    val agree_terms: String = "",

    @Json(name = "device_name")
    val device_name: String = "",

    @Json(name = "device_token")
    val device_token: String = "",

    )