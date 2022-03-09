package com.example.kuver.data.model

import com.squareup.moshi.Json

data class LoginResponse(

    @Json(name = "status")
    val status: String = "",

    @Json(name = "message")
    val message: String = "",

)


