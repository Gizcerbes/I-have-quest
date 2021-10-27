package com.uogames.i_have_quest.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uogames.i_have_quest.MainViewModel
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.di.ViewModelFactory
import com.uogames.i_have_quest.models.PermissionModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }

    @Inject
    lateinit var modelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            modelFactory
        ).get(MainViewModel::class.java)
    }

    private val permissionModel by lazy { ViewModelProvider(this).get(PermissionModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", viewModel.toString())

        val nav = findViewById<BottomNavigationView>(R.id.bottom_navi)

        val navController = findNavController(R.id.nav_host_fragment)
        nav.setupWithNavController(navController)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionModel.setLocationPermission(false)
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    permissionModel.setLocationPermission(true)
                }
            }
        }
    }

}