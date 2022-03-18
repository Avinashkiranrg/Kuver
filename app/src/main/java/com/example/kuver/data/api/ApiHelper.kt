package com.example.kuver.data.api

import com.example.kuver.data.model.*

interface ApiHelper {

    suspend fun loginRequest(loginRequest: LoginRequest): LoginResponse

    suspend fun getRegistration(registrationRequest: RegistrationRequestModel): RegistrationResponseModel

    suspend fun getCategory(catRequest: CatRequestModel): CatResponseModel

    suspend fun getSubCategary(subCatRequestModel: SubCatRequestModel): SubCatResponseModel

    suspend fun getCities(citiesRequestModel: CitiesRequestModel): CitiesResponseModel
}