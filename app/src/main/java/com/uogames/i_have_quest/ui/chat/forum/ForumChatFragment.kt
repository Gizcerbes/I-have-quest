package com.uogames.i_have_quest.ui.chat.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uogames.i_have_quest.databinding.FragmentChatForumBinding

class ForumChatFragment: Fragment() {

	private lateinit var bind: FragmentChatForumBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentChatForumBinding.inflate(inflater,container,false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

	}


}