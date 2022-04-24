package com.uogames.i_have_quest.ui.person.characteristic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uogames.i_have_quest.databinding.CardCharacteristicBinding
import com.uogames.i_have_quest.di.ViewModelFactory
import com.uogames.i_have_quest.ui.person.PersonViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CharacteristicFragment : DaggerFragment() {

	@Inject
	lateinit var factory: ViewModelFactory
	private val model by lazy {
		ViewModelProvider(requireActivity(), factory)[PersonViewModel::class.java]
	}

	private lateinit var bind: CardCharacteristicBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = CardCharacteristicBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

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