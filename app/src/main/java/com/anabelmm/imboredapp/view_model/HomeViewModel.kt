package com.anabelmm.imboredapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anabelmm.imboredapp.model.ActivityCard
import com.anabelmm.imboredapp.model.Repository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = Repository()
    val homeModel = MutableLiveData<ActivityCard?>()
    val isGifVisible = MutableLiveData(true)

    /**
     * Returns an [ActivityCard] from the Repository.
     */
    fun getActivity() {
        viewModelScope.launch {
            val result = repository.getActivityFromAPI()
            if (result != null)
                homeModel.postValue(result)
            isGifVisible.postValue(false)
        }
    }
}