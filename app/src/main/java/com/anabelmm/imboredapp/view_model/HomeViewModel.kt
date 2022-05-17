package com.anabelmm.imboredapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.anabelmm.imboredapp.model.ActivityCard
import com.anabelmm.imboredapp.model.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val homeModel = MutableLiveData<ActivityCard?>()
    val isGifVisible = MutableLiveData(true)

    /**
     * Returns an [ActivityCard] from the Repository.
     */
    fun getActivity() {
        viewModelScope.launch {
            val activityCard = repository.getActivityFromAPI()
            if (activityCard != null) {
                homeModel.postValue(activityCard)
                insertActivityToDB(activityCard)
            }
            isGifVisible.postValue(false)

        }
    }

    private suspend fun insertActivityToDB(card: ActivityCard) {
        repository.insertActivityCardToDB(card)
        homeModel.postValue(card)
    }
}

class HomeViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}