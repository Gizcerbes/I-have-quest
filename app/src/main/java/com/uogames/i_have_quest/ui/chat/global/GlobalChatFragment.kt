package com.uogames.i_have_quest.ui.chat.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uogames.i_have_quest.databinding.FragmentChatGlobalBinding

class GlobalChatFragment: Fragment() {

	private lateinit var bind: FragmentChatGlobalBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentChatGlobalBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

	}



}