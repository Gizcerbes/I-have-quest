package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MapStyleOptions
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentMapsBinding
import com.uogames.i_have_quest.models.LocationModel
import com.uogames.i_have_quest.models.PermissionModel

class MapsFragment : Fragment() {

    //private val navigationMenu by lazy { view?.findViewById<BottomNavigationView>(R.id.bottom_navi) }
    private val mapFragment by lazy { childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment? }

    private var googleMap: GoogleMap? = null

    private val permissionModel by lazy {
        ViewModelProvider(this.requireActivity()).get(
            PermissionModel::class.java
        )
    }

    private val locationModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(LocationModel::class.java)
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
        super.onViewCreated(view, savedInstanceState)
        mapFragment?.getMapAsync { onMapReady(it) }
        initNavigationMenu()
        permissionModel.locationPermission.observe(requireActivity()) { updateLocationUI(it) }
        locationModel.myLatLng.observe(this.requireActivity()) {
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 15f))
        }
        permissionModel.getLocationPermission(requireActivity())
    }

    fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.uiSettings.isZoomControlsEnabled = false
        googleMap.uiSettings.isScrollGesturesEnabled = false
        googleMap.uiSettings.isZoomGesturesEnabled = false
        googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this.requireActivity(),
                R.raw.map_style
            )
        )
    }

    private fun updateLocationUI(access: Boolean) {
        if (mapFragment == null) {
            return
        }
        try {
            if (access) {
                googleMap?.isMyLocationEnabled = true
                googleMap?.uiSettings?.isMyLocationButtonEnabled = false
                locationModel.startService()
            } else {
                googleMap?.isMyLocationEnabled = false
                googleMap?.uiSettings?.isMyLocationButtonEnabled = false
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun initNavigationMenu() {
        binding.bottomNavi.selectedItemId = R.id.item_map
        binding.bottomNavi.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_map -> {
                    false
                }
                R.id.item_quest -> {
                    view?.findNavController()?.navigate(R.id.questFragment)
                    true
                }
                R.id.item_basket -> {
                    view?.findNavController()
                        ?.navigate(R.id.backpackFragment)
                    true
                }
                R.id.item_person -> {
                    view?.findNavController()?.navigate(R.id.personFragment)
                    true
                }
                R.id.item_chat -> {
                    view?.findNavController()?.navigate(R.id.chatFragment)
                    true
                }
                else -> false
            }
        }
    }

}