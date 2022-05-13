package com.anabelmm.imboredapp.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_ULR = "https://www.boredapi.com/"
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_ULR)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface APIActivityService {
    @GET("/api/activity/")
    suspend fun getActivity() : Response<ActivityCard>
}

object APIActivity{
    val retrofitService : APIActivityService by lazy {
        retrofit.create(APIActivityService::class.java)
    }
}