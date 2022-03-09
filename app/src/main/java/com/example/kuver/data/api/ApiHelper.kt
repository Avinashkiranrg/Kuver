package com.example.kuver.data.api

import com.example.kuver.data.model.LoginRequest
import com.example.kuver.data.model.LoginResponse

interface ApiHelper {

    suspend fun loginRequest(responseModel : LoginRequest):LoginResponse
}