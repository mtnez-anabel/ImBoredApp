package com.anabelmm.imboredapp.ui.about_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anabelmm.imboredapp.R

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        
        value = ""
    }
    val text: LiveData<String> = _text
}