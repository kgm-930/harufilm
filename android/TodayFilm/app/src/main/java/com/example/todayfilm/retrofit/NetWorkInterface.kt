package com.example.todayfilm.retrofit

import com.example.todayfilm.data.SignupData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetWorkInterface {
    @FormUrlEncoded
    @POST("posts/")
    fun signUp(
        @Field("id") id:String,
        @Field("pw") pw:String,

    ): Call<SignupData>
}