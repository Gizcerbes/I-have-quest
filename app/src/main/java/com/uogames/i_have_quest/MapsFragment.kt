package com.uogames.i_have_quest

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapsFragment : Fragment() {

    private val navigationMenu by lazy { view?.findViewById<BottomNavigationView>(R.id.bottom_navi) }
    private val mapFragment by lazy { childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment? }


    // The entry point to the Fused Location Provider.
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }
    private var googleMap: GoogleMap? = null

    private val permissionModel by lazy {
        ViewModelProvider(this.requireActivity()).get(
            PermissionModel::class.java
        )
    }

    private val locationModel by lazy { ViewModelProvider(requireActivity()).get(LocationModel::class.java) }

    private val defaultLocation = LatLng(-33.8523341, 151.2106085)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment?.getMapAsync { onMapReady(it) }
        initNavigationMenu()
        permissionModel.locationPermission.observe(this.requireActivity()) {
            updateLocationUI(it)
        }
        locationModel.myLatLng.observe(this.requireActivity()) {
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 15f))
        }
        getLocationPermission()
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
                startLocationsUpdates()
            } else {
                googleMap?.isMyLocationEnabled = false
                googleMap?.uiSettings?.isMyLocationButtonEnabled = false
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationsUpdates() {
        val locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    locationModel.setMyLatLng(LatLng(location.latitude, location.longitude))
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

    private fun isLocationPermission(): Boolean {
        val locationPermissionGranted = context?.let {
            ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)
        } == PackageManager.PERMISSION_GRANTED
        permissionModel.setLocationPermission(locationPermissionGranted)
        return locationPermissionGranted
    }


    private fun getLocationPermission() {
        if (!isLocationPermission()) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MainActivity.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }


    private fun initNavigationMenu() {
        navigationMenu?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_map -> {
                    Toast.makeText(this.context, "map", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_quest -> {
                    Toast.makeText(this.context, "quest", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_basket -> {
                    Toast.makeText(this.context, "basket", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_person -> {
                    Toast.makeText(this.context, "person", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

}