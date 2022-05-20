package com.anabelmm.imboredapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.anabelmm.imboredapp.model.ActivityCard
import com.anabelmm.imboredapp.model.RepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RepositoryImp) : ViewModel() {
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
