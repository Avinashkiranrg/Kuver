package com.example.kuver.data.api

import com.example.kuver.data.model.LoginRequest
import com.example.kuver.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun getLoginRequest(@Body loginRequest: LoginRequest): LoginResponse



}