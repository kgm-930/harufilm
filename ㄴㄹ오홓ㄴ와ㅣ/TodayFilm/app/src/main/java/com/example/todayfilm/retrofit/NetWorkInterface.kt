package com.example.todayfilm.retrofit

import com.example.todayfilm.data.SignupData
import com.example.todayfilm.data.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface NetWorkInterface {

    @POST("user")
    fun signUp(
        @Body user: User
//        @Field("id") id:String,
//        @Field("pw") pw:String,
//        @Part file: MultipartBody.Part

    ): Call<SignupData>
}