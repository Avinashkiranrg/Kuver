package com.example.kuver.data.repository

import com.example.kuver.data.api.ApiHelperImpl
import com.example.kuver.data.model.LoginRequest

class MainRepository(private val apiHelper: ApiHelperImpl) {

    suspend fun getLoginRequest(requestModel : LoginRequest) = apiHelper.loginRequest(requestModel)
}