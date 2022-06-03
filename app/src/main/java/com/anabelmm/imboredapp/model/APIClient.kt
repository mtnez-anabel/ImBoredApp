package com.anabelmm.imboredapp.model

import android.util.Log
import javax.inject.Inject

class APIClient @Inject constructor(private val apiActivityService: APIActivityService) {
    suspend fun response(): ActivityCard? {
        val response = apiActivityService.getActivity()
        //isSuccessful() returns true if code() is in the range [200..300)
        if (!response.isSuccessful)
            return null
        return response.body()
    }
}