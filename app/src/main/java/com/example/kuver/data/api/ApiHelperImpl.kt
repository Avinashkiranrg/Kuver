package com.example.kuver.data.api

import com.example.kuver.data.model.LoginRequest
import com.example.kuver.data.model.LoginResponse
import com.example.kuver.data.model.RegistrationRequestModel
import com.example.kuver.data.model.RegistrationResponseModel

class ApiHelperImpl(private val apiService: ApiService):ApiHelper {

    override suspend fun loginRequest(responseModel: LoginRequest): LoginResponse {
        return apiService.getLoginRequest(responseModel)
    }

    override suspend fun getRegistration(responseRegistration: RegistrationRequestModel): RegistrationResponseModel {
        return apiService.getRegistration(responseRegistration)
    }

}