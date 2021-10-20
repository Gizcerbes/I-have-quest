package com.uogames.i_have_quest.ui.main_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentSecondNavigationBinding

class SecondNavigationFragment : Fragment() {

    private lateinit var binding: FragmentSecondNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = binding.frgNav.findNavController()
        binding.bottomNavi.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_map -> navController.navigate(R.id.navigation_map)
                R.id.navigation_chat -> navController.navigate(R.id.navigation_chat)
                R.id.navigation_quest -> navController.navigate(R.id.navigation_quest)
                R.id.navigation_backpack -> navController.navigate(R.id.navigation_backpack)
                R.id.navigation_person -> navController.navigate(R.id.navigation_person)
            }
            true
        }
    }
}