package com.uogames.i_have_quest.models

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PermissionModel @Inject constructor(private  val app: Application) : ViewModel() {

	companion object {
		const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
	}

	// location permission
	private val _locationPermission = MutableLiveData<Boolean>()
	val locationPermission = _locationPermission

	fun setLocationPermission(permission: Boolean) {
		_locationPermission.postValue(permission)
	}

	private fun isLocationPermission(): Boolean {
		val locationPermissionGranted = app.let {
			ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)
		} == PackageManager.PERMISSION_GRANTED
		setLocationPermission(locationPermissionGranted)
		return locationPermissionGranted
	}

	fun getLocationPermission(activity: Activity) {
		if (!isLocationPermission()) {
			ActivityCompat.requestPermissions(
				activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
				PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
			)
		}
	}

	fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		setLocationPermission(false)
		when (requestCode) {
			PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.isNotEmpty() &&
					grantResults[0] == PackageManager.PERMISSION_GRANTED
				) {
					setLocationPermission(true)
				}
			}
		}
	}

}