package com.uogames.i_have_quest.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
		model.title.onEach { bind.tvTitle.text = it }.launchIn(lifecycleScope)
		model.username.onEach { bind.tvName.text = it }.launchIn(lifecycleScope)
		model.lvl.onEach { bind.tvLvl.text = it }.launchIn(lifecycleScope)
		model.force.onEach { bind.tvForce.text = it }.launchIn(lifecycleScope)
		model.defence.onEach { bind.tvDefence.text = it }.launchIn(lifecycleScope)
		model.agility.onEach { bind.tvAgility.text = it }.launchIn(lifecycleScope)
		val changeHealth: suspend (Any) -> Unit = {
			val vitality = model.vitality.value
			val health = model.health.value
			val maxHealth = model.maxHealth.value
			bind.tvHealth.text = "$vitality ($health/$maxHealth)"
		}
		model.vitality.onEach(changeHealth).launchIn(lifecycleScope)
		model.health.onEach(changeHealth).launchIn(lifecycleScope)
		model.maxHealth.onEach(changeHealth).launchIn(lifecycleScope)
		model.experience.onEach { bind.tvExperience.text = it }.launchIn(lifecycleScope)
	}
}