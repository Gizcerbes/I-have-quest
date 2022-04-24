package com.uogames.i_have_quest.ui.maps.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uogames.i_have_quest.databinding.FragmentMapsMenuBinding

class MapsMenuFragment: Fragment() {

	private lateinit var bind: FragmentMapsMenuBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentMapsMenuBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

		super.onViewCreated(view, savedInstanceState)
	}

}