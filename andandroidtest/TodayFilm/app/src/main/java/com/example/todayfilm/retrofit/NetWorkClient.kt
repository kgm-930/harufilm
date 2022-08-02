package com.example.todayfilm.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWorkClient {
    private const val BASE_URL = "http://10.0.2.2:8000/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val GetNetwork: NetWorkInterface = retrofit.create(NetWorkInterface::class.java)
}