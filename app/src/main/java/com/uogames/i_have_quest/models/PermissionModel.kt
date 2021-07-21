package com.uogames.i_have_quest.models

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.ui.AppBarConfiguration
import com.uogames.i_have_quest.ui.MainActivity

class PermissionModel(application: Application) : AndroidViewModel(application) {
    private val _locationPermission = MutableLiveData<Boolean>()
    val locationPermission = _locationPermission

    val appBarConfiguration = MutableLiveData<AppBarConfiguration>()

    fun setLocationPermission(permission: Boolean) {
        _locationPermission.postValue(permission)
    }

    private fun isLocationPermission(): Boolean {
        val locationPermissionGranted = getApplication<Application>().applicationContext.let {
            ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)
        } == PackageManager.PERMISSION_GRANTED
        setLocationPermission(locationPermissionGranted)
        return locationPermissionGranted
    }

    fun getLocationPermission(activity: Activity) {
        if (!isLocationPermission()) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MainActivity.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

}