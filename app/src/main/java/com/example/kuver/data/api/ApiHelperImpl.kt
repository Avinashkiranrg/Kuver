package com.example.kuver.data.api

import com.example.kuver.data.model.*

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun loginRequest(loginRequest: LoginRequest): LoginResponse {
        return apiService.getLoginRequest(loginRequest)
    }

    override suspend fun getRegistration(registrationRequest: RegistrationRequestModel): RegistrationResponseModel {
        return apiService.getRegistration(registrationRequest)
    }

    override suspend fun getCategory(catRequest: CatRequestModel): CatResponseModel {
        return apiService.getCategory(catRequest.`API-KEY`, catRequest.lang)
    }

    override suspend fun getSubCategary(subCatRequestModel: SubCatRequestModel): SubCatResponseModel {
        return apiService.getSubCategory(
            subCatRequestModel.`API-KEY`,
            subCatRequestModel.lang,
            subCatRequestModel.category_id
        )
    }

    override suspend fun getCities(citiesRequestModel: CitiesRequestModel): CitiesResponseModel {
        return apiService.getCities(citiesRequestModel.`API-KEY`, citiesRequestModel.lang)
    }

    override suspend fun getHomeItems(homeRequestModel: HomeRequestModel): HomeResponseModel {
        return apiService.getHomeItems(
            homeRequestModel.`API-KEY`,
            homeRequestModel.lang,
            homeRequestModel.category_id,
            homeRequestModel.sub_category_id,
            homeRequestModel.post_type,
            homeRequestModel.city_id,
            homeRequestModel.latitude,
            homeRequestModel.longitude,
            homeRequestModel.order_by,
            homeRequestModel.page,
        )
    }
}