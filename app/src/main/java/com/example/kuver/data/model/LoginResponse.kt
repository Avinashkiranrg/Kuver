package com.example.kuver.data.model

import com.squareup.moshi.Json

data class LoginResponse(

    @Json(name = "status")
    val status: String = "",

    @Json(name = "message")
    val message: String = "",

    @Json(name = "user_details")
    val user_details: UserDetails?=null,

    @Json(name = "base_path")
    val base_path: String = "",

)

data class UserDetails(

    @Json(name = "user_id" )
    val user_id : String = "",

    @Json(name = "full_name")
    val full_name: String = "",

    @Json(name = "last_name")
    val last_name: String = "",

    @Json(name = "email")
    val email: String = "",

    @Json(name = "phone")
    val phone: String = "",

    @Json(name = "password")
    val password: String = "",

    @Json(name = "otp")
    val otp: String = "",

    @Json(name = "otp_status")
    val otp_status: String = "",

    @Json(name = "profile_pic")
    val profile_pic: String = "",

)


