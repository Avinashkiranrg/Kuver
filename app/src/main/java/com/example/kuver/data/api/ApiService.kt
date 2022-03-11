package com.example.kuver.data.api

import com.example.kuver.data.model.LoginRequest
import com.example.kuver.data.model.LoginResponse
import com.example.kuver.data.model.RegistrationRequestModel
import com.example.kuver.data.model.RegistrationResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun getLoginRequest(@Body loginRequest: LoginRequest): LoginResponse

    @POST("user_registration")
    suspend fun getRegistration(@Body registrationRequestModel: RegistrationRequestModel):RegistrationResponseModel

}