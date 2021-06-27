package com.uogames.i_has_quest

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        const val DEFAULT_ZOOM = 15
    }

    private val permissionModel by lazy { ViewModelProvider(this).get(PermissionModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionModel.setLocationPermission(true)
                }
            }
        }

    }

}