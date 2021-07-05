package com.uogames.i_have_quest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PermissionModel : ViewModel(){
    private val _locationPermission = MutableLiveData<Boolean>()
    val locationPermission = _locationPermission

    fun setLocationPermission(permission:Boolean){
        _locationPermission.postValue(permission)
    }

}