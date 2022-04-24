package com.uogames.i_have_quest.ui.maps

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MapViewModel @Inject constructor() : ViewModel() {

	private val zoomControlEnabled = MutableStateFlow(true)
	val isZoomControlEnabled = zoomControlEnabled.asStateFlow()

	private val scrollGesturesEnabled = MutableStateFlow(true)
	val isScrollGesturesEnabled = scrollGesturesEnabled.asStateFlow()

	private val zoomGesturesEnabled = MutableStateFlow(true)
	val isZoomGesturesEnabled = zoomGesturesEnabled.asStateFlow()

	private val _zoom = MutableStateFlow(15f)
	val zoom = _zoom.asStateFlow()

	private val _target = MutableStateFlow(LatLng(0.0, 0.0))
	val target = _target.asStateFlow()

	private val _isMyLocationEnabled = MutableStateFlow(true)
	val isMyLocationEnabled = _isMyLocationEnabled.asStateFlow()

	private val _isMyLocationButtonEnabled = MutableStateFlow(true)
	val isMyLocationButtonEnabled = _isMyLocationButtonEnabled.asStateFlow()

	private val _isShowMyPosition = MutableStateFlow(true)
	val isShowMyPosition = _isShowMyPosition.asStateFlow()

	fun setNewTarget(target: LatLng, zoom: Float) {
		this._target.value = target
		this._zoom.value = zoom
	}

	fun setMyLocationEnabled(boolean: Boolean) {
		_isMyLocationButtonEnabled.value = boolean
		_isMyLocationEnabled.value = boolean
	}

	fun setShowMyPosition(boolean: Boolean){
		_isShowMyPosition.value = boolean
	}

}