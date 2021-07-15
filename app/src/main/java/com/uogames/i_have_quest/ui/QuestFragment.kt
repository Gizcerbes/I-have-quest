package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentQuestBinding

class QuestFragment: Fragment() {

    private val navigationMenu by lazy { view?.findViewById<BottomNavigationView>(R.id.bottom_navi) }
    private lateinit var binding : FragmentQuestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quest, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigationMenu()
    }

    private fun initNavigationMenu() {
        navigationMenu?.selectedItemId = R.id.item_quest
        navigationMenu?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_map -> {
                    view?.findNavController()?.navigate(R.id.action_questFragment_to_mapsFragment)
                    true
                }
                R.id.item_quest -> {
                    false
                }
                R.id.item_basket -> {
                    view?.findNavController()?.navigate(R.id.action_questFragment_to_backpackFragment)
                    true
                }
                R.id.item_person -> {
                    view?.findNavController()?.navigate(R.id.action_questFragment_to_personFragment)
                    true
                }
                else -> false
            }
        }

    }

}