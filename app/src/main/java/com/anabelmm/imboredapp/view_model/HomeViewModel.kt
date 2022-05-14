package com.anabelmm.imboredapp.view_model

import android.content.Context
import androidx.lifecycle.*
import com.anabelmm.imboredapp.model.ActivityCard
import com.anabelmm.imboredapp.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(context: Context, scope: CoroutineScope) : ViewModel() {
    //, state: SavedStateHandle
    private val repository = Repository(context, scope)
    val homeModel = MutableLiveData<ActivityCard?>()
    //val isVisible = MutableLiveData<Boolean>()


    fun getActivity() {
        viewModelScope.launch {
            val result = repository.getActivityFromAPI()
            if (result != null)
                homeModel.postValue(result)
            //isVisible.postValue(false)
        }
    }
}