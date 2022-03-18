package com.example.kuver.data.api

import com.example.kuver.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun getLoginRequest(@Body loginRequest: LoginRequest): LoginResponse

    @POST("user_registration")
    suspend fun getRegistration(@Body registrationRequestModel: RegistrationRequestModel): RegistrationResponseModel

    @GET("categories")
    suspend fun getCategory(
        @Query("API-KEY") `API-KEY`: String,
        @Query("lang") lang: String
    ): CatResponseModel

    @GET("category_subcat")
    suspend fun getSubCategory(
        @Query("API-KEY") `API-KEY`: String,
        @Query("lang") lang: String,
        @Query("category_id") category_id: String
    ): SubCatResponseModel

    @GET("cities")
    suspend fun getCities(
        @Query("API-KEY") `API-KEY`: String,
        @Query("lang") lang: String
    ): CitiesResponseModel

}