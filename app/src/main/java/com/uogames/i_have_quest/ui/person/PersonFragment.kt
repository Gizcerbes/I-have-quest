package com.uogames.i_have_quest.ui.person

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentPersonBinding
import com.uogames.i_have_quest.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PersonFragment : DaggerFragment() {

	@Inject
	lateinit var factory: ViewModelFactory
	private val model by lazy {
		ViewModelProvider(requireActivity(), factory)[PersonViewModel::class.java]
	}

	private lateinit var bind: FragmentPersonBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentPersonBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		model.update()

	}
}