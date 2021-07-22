package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentChatInfoBinding
import com.uogames.i_have_quest.models.NetworkModel
import com.uogames.i_have_quest.ui.adapters.ChatSelectAdapter

class ChatInfoFragment : Fragment() {

    lateinit var binding: FragmentChatInfoBinding
    private val networkModel by lazy { ViewModelProvider(requireActivity()).get(NetworkModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.clSearch.visibility = View.VISIBLE
        binding.tlTabChatLayout.addOnTabSelectedListener(initMenu())
        networkModel.countPersonalChats.observe(requireActivity()){
            binding.rvFragmentChat.adapter = ChatSelectAdapter(it.toInt(), ChatSelectAdapter.PERSONAL, networkModel, context)
        }
        networkModel.countForumChats.observe(requireActivity()){
            binding.rvFragmentChat.adapter = ChatSelectAdapter(it.toInt(), ChatSelectAdapter.FORUM, networkModel, context)
        }
        networkModel.updateCountPersonalChats()
    }

    private fun initMenu(): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { tabSelect(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.let { tabSelect(it) }
            }

            private fun tabSelect(tab: TabLayout.Tab){
                when (tab.text) {
                    getString(R.string.global) -> {
                        binding.clSearch.visibility = View.GONE
                        val adapter = ChatSelectAdapter(1, ChatSelectAdapter.GLOBAL, networkModel, requireContext())
                        binding.rvFragmentChat.adapter = adapter
                    }
                    getText(R.string.personal) -> {
                        binding.clSearch.visibility = View.VISIBLE
                        networkModel.updateCountPersonalChats()
                    }
                    getText(R.string.forum) -> {
                        binding.clSearch.visibility = View.VISIBLE
                        networkModel.updateCountForumChats()
                    }
                }
            }

        }
    }
}