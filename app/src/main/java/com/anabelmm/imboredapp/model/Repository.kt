package com.anabelmm.imboredapp.model

import androidx.room.ColumnInfo
import com.anabelmm.imboredapp.model.db.CardDAO
import com.anabelmm.imboredapp.model.db.CardEntity

class Repository() {
    private val api = APIClient()

    suspend fun getActivityFromAPI(): ActivityCard? = api.getActivity()
    
}
