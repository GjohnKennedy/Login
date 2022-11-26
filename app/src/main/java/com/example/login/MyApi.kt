package com.example.login

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {

 @POST("api/login/")

fun getUserData(@Body body: RequestBody) : Call<List<MyData>>

    companion object {

        var BASE_URL = "http://192.168.1.4:8000/"

        fun create() : MyApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(MyApi::class.java)

        }
    }

}