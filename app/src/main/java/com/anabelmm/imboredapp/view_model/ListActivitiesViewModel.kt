package com.anabelmm.imboredapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anabelmm.imboredapp.model.ActivityCard
import com.anabelmm.imboredapp.model.Repository

class ListActivitiesViewModel(private val repository: Repository) : ViewModel() {

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

class ListActivitiesViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListActivitiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListActivitiesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}