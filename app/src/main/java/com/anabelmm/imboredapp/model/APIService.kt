package com.anabelmm.imboredapp.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_ULR = "https://www.boredapi.com/"

/**
 * Builds a Retrofit object with a base URI and a converter factory to build a web service API.
 * The converter tells Retrofit what to do with the data it gets back from the web service.
 * It is using the default timeouts settings.
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_ULR)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

/**
 * Interface that defines a GET HTTP operation.
 */
interface APIActivityService {
    @GET("/api/activity/")
    suspend fun getActivity(): Response<ActivityCard>
}

/**
 * Lazily instantiates a singleton object that implements [APIActivityService].
 */
object APIActivity {
    val retrofitService: APIActivityService by lazy {
        retrofit.create(APIActivityService::class.java)
    }
}