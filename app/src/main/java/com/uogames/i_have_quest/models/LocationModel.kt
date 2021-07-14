package com.uogames.i_have_quest.models


import android.annotation.SuppressLint
import android.app.Application
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class LocationModel(application: Application) : AndroidViewModel(application) {

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(application.applicationContext)
    }

    private val _myLatLng = MutableLiveData(LatLng(-33.8523341, 151.2106085))
    val myLatLng: LiveData<LatLng> = _myLatLng

    fun setMyLatLng(latLng:LatLng){
        _myLatLng.postValue(latLng)
    }

    @SuppressLint("MissingPermission")
    fun startService(){
        val locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    setMyLatLng(LatLng(location.latitude, location.longitude))
                }
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(
            getRequest(),
            locationCallBack,
            Looper.getMainLooper()
        )
    }

    private fun getRequest() = LocationRequest.create().apply {
        interval = 2000
        fastestInterval = 1000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

}