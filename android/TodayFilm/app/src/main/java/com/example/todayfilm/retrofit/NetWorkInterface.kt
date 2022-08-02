package com.example.todayfilm.retrofit

import com.example.todayfilm.data.LoginData
import com.example.todayfilm.data.SignupData
import com.example.todayfilm.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetWorkInterface {
    @POST("api/account/signup")
    fun signUp(
        @Body user: User
    ): Call<SignupData>

    @POST("api/account/signin")
    fun login(
        @Body user: User
    ): Call<LoginData>
}