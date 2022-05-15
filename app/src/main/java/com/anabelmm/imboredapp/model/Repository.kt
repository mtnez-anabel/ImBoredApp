package com.anabelmm.imboredapp.model

class Repository() {
    private val api = APIClient()

    suspend fun getActivityFromAPI(): ActivityCard? = api.getActivity()

}
