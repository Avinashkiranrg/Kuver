package com.example.kuver.data.api

import com.example.kuver.data.model.LoginRequest
import com.example.kuver.data.model.LoginResponse

class ApiHelperImpl(private val apiService: ApiService):ApiHelper {

    override suspend fun loginRequest(responseModel: LoginRequest): LoginResponse {
        return apiService.getLoginRequest(responseModel)
    }

}