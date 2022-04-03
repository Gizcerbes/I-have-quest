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
		val tvTitle = view.findViewById<TextView>(R.id.tv_title)
		val tvName = view.findViewById<TextView>(R.id.tv_name)
		val tvLvl = view.findViewById<TextView>(R.id.tv_lvl)

		model.title.onEach { tvTitle.text = it }.launchIn(lifecycleScope)
		model.username.onEach { tvName.text = it }.launchIn(lifecycleScope)
		model.lvl.onEach { tvLvl.text = it }.launchIn(lifecycleScope)

		val tvForce = view.findViewById<TextView>(R.id.tv_force)
		val tvDefence = view.findViewById<TextView>(R.id.tv_defence)
		val tvAgility = view.findViewById<TextView>(R.id.tv_agility)
		val tvHealth = view.findViewById<TextView>(R.id.tv_health)
		val tvExperience = view.findViewById<TextView>(R.id.tv_experience)

		model.force.onEach { tvForce.text = it }.launchIn(lifecycleScope)
		model.defence.onEach { tvDefence.text = it }.launchIn(lifecycleScope)
		model.agility.onEach { tvAgility.text = it }.launchIn(lifecycleScope)
		val changeHealth: suspend (Any) -> Unit = {
			val vitality = model.vitality.value
			val health = model.health.value
			val maxHealth = model.maxHealth.value
			tvHealth.text = "$vitality ($health/$maxHealth)"
		}
		model.vitality.onEach(changeHealth).launchIn(lifecycleScope)
		model.health.onEach(changeHealth).launchIn(lifecycleScope)
		model.maxHealth.onEach(changeHealth).launchIn(lifecycleScope)
		model.experience.onEach { tvExperience.text = it }.launchIn(lifecycleScope)

	}
}