package com.example.todayfilm.retrofit

import com.example.todayfilm.data.LoginData
import com.example.todayfilm.data.SignupData
import com.example.todayfilm.data.User
import retrofit2.Call
import retrofit2.http.*

interface NetWorkInterface {

    @POST("signup")
    fun signUp(
        @Body user: User
    ): Call<SignupData>

    @POST("login")
    fun login(
        @Body user: User
    ): Call<LoginData>
}