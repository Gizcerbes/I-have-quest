package com.uogames.i_have_quest.models


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.uogames.model.GameProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LocationModel @Inject constructor(context: Context, private val gameProvider: GameProvider) :
	ViewModel() {

	private val fusedLocationProviderClient by lazy {
		LocationServices.getFusedLocationProviderClient(context)
	}

	private val _myLatLng = MutableStateFlow(LatLng(0.0, 0.0))
	val myLatLng = _myLatLng.asStateFlow()

	fun setMyLatLng(latLng: LatLng) {
		_myLatLng.value = latLng
		gameProvider.setMyLocation(latLng.latitude.toString(), latLng.longitude.toString())
	}

	@SuppressLint("MissingPermission")
	fun startService() {
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