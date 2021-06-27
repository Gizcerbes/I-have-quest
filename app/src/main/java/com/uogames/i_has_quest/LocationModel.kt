package com.uogames.i_has_quest


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class LocationModel : ViewModel() {

    private val _myLatLng = MutableLiveData(LatLng(-33.8523341, 151.2106085))
    val myLatLng: LiveData<LatLng> = _myLatLng


    fun setMyLatLng(latLng:LatLng){
        _myLatLng.postValue(latLng)
    }


}