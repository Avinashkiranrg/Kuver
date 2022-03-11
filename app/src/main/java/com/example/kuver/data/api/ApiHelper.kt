package com.example.kuver.data.api

import com.example.kuver.data.model.LoginRequest
import com.example.kuver.data.model.LoginResponse
import com.example.kuver.data.model.RegistrationRequestModel
import com.example.kuver.data.model.RegistrationResponseModel

interface ApiHelper {

    suspend fun loginRequest(responseModel : LoginRequest):LoginResponse

    suspend fun getRegistration(responseRegistration : RegistrationRequestModel):RegistrationResponseModel
}