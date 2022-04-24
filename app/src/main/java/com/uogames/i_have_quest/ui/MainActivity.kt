package com.uogames.i_have_quest.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.di.ViewModelFactory
import com.uogames.i_have_quest.models.PermissionModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

	@Inject
	lateinit var factory: ViewModelFactory

	private val permissionModel by lazy {
		ViewModelProvider(this, factory)[PermissionModel::class.java]
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

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
		permissionModel.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}

}