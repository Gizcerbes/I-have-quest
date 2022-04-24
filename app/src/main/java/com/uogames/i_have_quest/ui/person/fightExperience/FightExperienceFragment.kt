package com.uogames.i_have_quest.ui.person.fightExperience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uogames.i_have_quest.databinding.CardFightExperienceBinding
import dagger.android.support.DaggerFragment

class FightExperienceFragment: DaggerFragment() {

	private lateinit var bind: CardFightExperienceBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = CardFightExperienceBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}


}