package com.uogames.i_have_quest.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MapStyleOptions
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentMapsBinding
import com.uogames.i_have_quest.di.ViewModelFactory
import com.uogames.i_have_quest.models.LocationModel
import com.uogames.i_have_quest.models.PermissionModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MapFragment : DaggerFragment() {

	@Inject
	lateinit var factory: ViewModelFactory

	private val mapFragment by lazy { childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment? }

	private var googleMap: GoogleMap? = null

	private val permissionModel by lazy {
		ViewModelProvider(requireActivity(), factory)[PermissionModel::class.java]
	}

	private val locationModel by lazy {
		ViewModelProvider(requireActivity(), factory)[LocationModel::class.java]
	}

	private val mapModel by lazy {
		ViewModelProvider(requireActivity(), factory)[MapViewModel::class.java]
	}

	private lateinit var binding: FragmentMapsBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		mapFragment?.getMapAsync { onMapReady(it) }
		permissionModel.locationPermission.observe(requireActivity()) { updateLocationUI(it) }
		permissionModel.getLocationPermission(requireActivity())
	}

	private fun onMapReady(googleMap: GoogleMap) {
		this.googleMap = googleMap
		googleMap.setOnCameraMoveListener {
			googleMap.cameraPosition.run { mapModel.setNewTarget(target, zoom) }
			mapModel.setShowMyPosition(false)
		}
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationModel.myLatLng.value, 15f))
		googleMap.setMapStyle(
			MapStyleOptions.loadRawResourceStyle(
				this.requireActivity(),
				R.raw.map_style
			)
		)
		locationModel.myLatLng.onEach {
			if (mapModel.isShowMyPosition.value)
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, mapModel.zoom.value))
		}.launchIn(lifecycleScope)
		mapModel.isZoomControlEnabled.onEach {
			googleMap.uiSettings.isZoomControlsEnabled = it
		}.launchIn(lifecycleScope)
		mapModel.isScrollGesturesEnabled.onEach {
			googleMap.uiSettings.isScrollGesturesEnabled = it
		}.launchIn(lifecycleScope)
		mapModel.isZoomGesturesEnabled.onEach {
			googleMap.uiSettings.isZoomGesturesEnabled = it
		}.launchIn(lifecycleScope)
	}

	private fun updateLocationUI(access: Boolean) {
		if (mapFragment == null) return
		try {
			mapModel.setMyLocationEnabled(access)
			mapModel.isMyLocationEnabled.onEach {
				googleMap?.isMyLocationEnabled = it
			}.launchIn(lifecycleScope)
			mapModel.isMyLocationButtonEnabled.onEach {
				googleMap?.uiSettings?.isMyLocationButtonEnabled = it
			}.launchIn(lifecycleScope)
			googleMap?.setOnMyLocationButtonClickListener {
				mapModel.setShowMyPosition(true)
				false
			}
			if (access){
				locationModel.startService()
				mapModel.setShowMyPosition(true)
			}
		} catch (e: SecurityException) {
			Toast.makeText(requireActivity().applicationContext, "${e.message}", Toast.LENGTH_SHORT)
				.show()
		}
	}

}