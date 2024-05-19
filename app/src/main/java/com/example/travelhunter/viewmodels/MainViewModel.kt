package com.example.travelhunter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private var bottomNavVisibility = MutableLiveData<Boolean>()
    val getBottomNavVisibility : LiveData<Boolean> = bottomNavVisibility

    private  var customBack = MutableLiveData<Boolean>()
    val getCustomBack : LiveData<Boolean> = customBack



    fun setBottomNavVisibility(visibility: Boolean){
        bottomNavVisibility.value = visibility
    }

    fun enableCustomBack(enable: Boolean){
        customBack.value = enable
    }



    fun playAnimation(icon: LottieAnimationView, min:Float, max: Float, speed: Float, repeat: Int, mode:Int){
        icon.setMinAndMaxProgress(min, max)
        icon.repeatCount = repeat
        icon.repeatMode= mode
        icon.speed = speed
        icon.playAnimation()
    }


}