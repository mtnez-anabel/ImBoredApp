package com.anabelmm.imboredapp.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope

class Repository(context: Context, scope: CoroutineScope) {
    private val api = APIClient()

    suspend fun getActivityFromAPI(): ActivityCard? = api.getActivity()

}
