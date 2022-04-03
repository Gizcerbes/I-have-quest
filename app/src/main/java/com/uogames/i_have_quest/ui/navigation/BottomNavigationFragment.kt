package com.uogames.i_have_quest.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentSecondNavigationBinding

class BottomNavigationFragment : Fragment() {

	private lateinit var bind: FragmentSecondNavigationBinding
	private val model by lazy { ViewModelProvider(requireActivity())[BottomNavigationViewModel::class.java] }

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentSecondNavigationBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		bind.bottomNavi.setOnItemSelectedListener {
			when (it.itemId) {
				R.id.navigation_map ->
					bind.navHostFragment.findNavController().navigate(R.id.navigation_map)
				R.id.navigation_chat ->
					bind.navHostFragment.findNavController().navigate(R.id.navigation_chat)
				R.id.navigation_quest ->
					bind.navHostFragment.findNavController().navigate(R.id.navigation_quest)
				R.id.navigation_backpack ->
					bind.navHostFragment.findNavController().navigate(R.id.navigation_backpack)
				R.id.navigation_person ->
					bind.navHostFragment.findNavController().navigate(R.id.navigation_person)
			}
			model.setItemID(it.itemId)
			true
		}
	}

	override fun onStart() {
		super.onStart()
		val selectedItemID = model.selectedItemID.value
		if (selectedItemID != -1) {
			bind.bottomNavi.selectedItemId = selectedItemID
		}
	}
}