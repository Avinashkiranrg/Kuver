package com.example.kuver.data.repository

import com.example.kuver.data.api.ApiHelperImpl
import com.example.kuver.data.model.*

class MainRepository(private val apiHelper: ApiHelperImpl) {

    suspend fun getLoginRequest(loginRequest: LoginRequest) = apiHelper.loginRequest(loginRequest)

    suspend fun getRegistrationRequest(registrationRequest: RegistrationRequestModel) =
        apiHelper.getRegistration(registrationRequest)

    suspend fun getCatRequest(catRequest: CatRequestModel) = apiHelper.getCategory(catRequest)

    suspend fun getSubCatRequest(subCatRequest: SubCatRequestModel) =
        apiHelper.getSubCategary(subCatRequest)

    suspend fun getCitiesRequest(citiesRequest: CitiesRequestModel) =
        apiHelper.getCities(citiesRequest)
}