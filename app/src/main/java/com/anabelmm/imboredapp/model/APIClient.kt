package com.anabelmm.imboredapp.model

import retrofit2.Response

class APIClient {
    lateinit var response: Response<ActivityCard>
    suspend fun getActivity(): ActivityCard? {
        response = APIActivity.retrofitService.getActivity()
        //isSuccessful() returns true if code() is in the range [200..300)
        if (!response.isSuccessful)
            return null
        return response.body()
    }
}