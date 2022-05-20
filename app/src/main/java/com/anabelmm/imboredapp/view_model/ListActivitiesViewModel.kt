package com.anabelmm.imboredapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anabelmm.imboredapp.model.ActivityCard
import com.anabelmm.imboredapp.model.RepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListActivitiesViewModel @Inject constructor(private val repository: RepositoryImp) :
    ViewModel() {

    val listActivitiesModel = MutableLiveData<List<ActivityCard?>>()
    val listSize = MutableLiveData(0)

    suspend fun getFromDB(): List<ActivityCard?> {
        val list = repository.getListActivityFromDB()
        listActivitiesModel.postValue(list)
        listSize.postValue(list.size)
        return list
    }

    suspend fun deleteList() {
        repository.deleteAllCardActivities()
        listActivitiesModel.postValue(emptyList())
        listSize.postValue(0)
    }
}