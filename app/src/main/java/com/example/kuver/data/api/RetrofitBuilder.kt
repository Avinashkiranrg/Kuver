package com.example.kuver.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitBuilder {
    private const val BASE_URL = "http://maplenestinc.ca/tokh/services/"
   // private const val BASE_URL = "https://tokhapp.com/services/"
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(add())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private fun add(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return client

    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}