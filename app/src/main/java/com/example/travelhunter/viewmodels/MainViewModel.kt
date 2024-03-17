package com.example.travelhunter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.transition.Visibility

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private var bottom_nav_visibility = MutableLiveData<Boolean>()
    val get_bottom_nav_visibility : LiveData<Boolean> = bottom_nav_visibility


    fun setBottomNavVisibility(visibility: Boolean){
        bottom_nav_visibility.value = visibility
    }
}