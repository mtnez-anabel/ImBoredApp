package com.anabelmm.imboredapp.model

import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface that defines a GET HTTP operation.
 */
interface APIActivityService {
    @GET("/api/activity/")
    suspend fun getActivity(): Response<ActivityCard>
}

