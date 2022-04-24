package com.uogames.i_have_quest.ui.person.personInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uogames.i_have_quest.databinding.CardPersonalInfoBinding
import com.uogames.i_have_quest.di.ViewModelFactory
import com.uogames.i_have_quest.ui.person.PersonViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PersonInfoFragment : DaggerFragment() {

	@Inject
	lateinit var factory: ViewModelFactory
	private val model by lazy {
		ViewModelProvider(requireActivity(), factory)[PersonViewModel::class.java]
	}

	private lateinit var bind: CardPersonalInfoBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = CardPersonalInfoBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		model.title.onEach { bind.tvTitle.text = it }.launchIn(lifecycleScope)
		model.username.onEach { bind.tvName.text = it }.launchIn(lifecycleScope)
		model.lvl.onEach { bind.tvLvl.text = it }.launchIn(lifecycleScope)
	}
}