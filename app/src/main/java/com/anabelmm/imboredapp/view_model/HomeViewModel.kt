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
            val result = repository.getActivityFromAPI()
            if (result != null)
                homeModel.postValue(result)
            isGifVisible.postValue(false)
        }
    }

    suspend fun insertActivityToDB(card: ActivityCard) {
        repository.setActivityCardToDB(card)
    }

    suspend fun getFromDB(): List<ActivityCard?> {
        return repository.getListActivityFromDB()
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