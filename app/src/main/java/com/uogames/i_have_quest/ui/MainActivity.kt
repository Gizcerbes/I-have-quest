package com.uogames.i_have_quest.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.models.NetworkModel
import com.uogames.i_have_quest.models.PermissionModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }

    private val permissionModel by lazy { ViewModelProvider(this).get(PermissionModel::class.java) }
    private val networkModel by lazy { ViewModelProvider(this).get(NetworkModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav = findViewById<BottomNavigationView>(R.id.bottom_navi)

        networkModel.loginData.observe(this) {
            if (it.user != null) {
                nav.visibility = View.VISIBLE
            } else {
                nav.visibility = View.GONE
            }
        }

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