package com.uogames.i_have_quest.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentChatInfoBinding

class ChatInfoFragment : Fragment() {

	lateinit var bind: FragmentChatInfoBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentChatInfoBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		bind.tlTabChatLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
			override fun onTabSelected(tab: TabLayout.Tab?) {

				when (tab?.position) {
					0 -> bind.navTabFragment.findNavController().navigate(R.id.personChatFragment)
					1 -> bind.navTabFragment.findNavController().navigate(R.id.globalChatFragment)
					2 -> bind.navTabFragment.findNavController().navigate(R.id.forumChatFragment)
				}
			}

			override fun onTabReselected(tab: TabLayout.Tab?) {
			}

			override fun onTabUnselected(tab: TabLayout.Tab?) {
			}
		})
	}
}