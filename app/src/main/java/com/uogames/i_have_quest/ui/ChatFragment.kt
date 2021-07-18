package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentChatBinding

class ChatFragment: Fragment() {

    lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        return binding.root
    }

    inner class Adapter: PagerAdapter(){
        override fun getCount(): Int {
            return  0;
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return false
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initNavigationMenu()
        binding.tlTabChatLayout.setOnTabSelectedListener(initMenu())

    }

    private fun initMenu():TabLayout.OnTabSelectedListener{
        return object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                Log.e("TAG", "selected " + tab?.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.e("TAG", "unselected " + tab?.text.toString())
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.e("TAG", "reselected " + tab?.text.toString())
            }

        }
    }

    private fun initNavigationMenu() {
        binding.bottomNavi.selectedItemId = R.id.item_chat
        binding.bottomNavi.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_map -> {
                    view?.findNavController()?.navigate(R.id.mapsFragment)
                    false
                }
                R.id.item_quest -> {
                    view?.findNavController()?.navigate(R.id.questFragment)
                    true
                }
                R.id.item_basket -> {
                    view?.findNavController()
                        ?.navigate(R.id.backpackFragment)
                    true
                }
                R.id.item_person -> {
                    view?.findNavController()?.navigate(R.id.personFragment)
                    true
                }
                R.id.item_chat -> {
                    false
                }
                else -> false
            }
        }
    }

}