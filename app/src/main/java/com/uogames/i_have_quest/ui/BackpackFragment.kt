package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentBackpackBinding

class BackpackFragment : Fragment() {

    private lateinit var binding: FragmentBackpackBinding
    //private val navigationMenu by lazy { view?.findViewById<BottomNavigationView>(R.id.bottom_navi) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_backpack, container, false)
        return binding.root //inflater.inflate(R.layout.fragment_backpack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initNavigationMenu()
    }

//    private fun initNavigationMenu() {
//        binding.bottomNavi.selectedItemId = R.id.navigation_backpack
//        binding.bottomNavi.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.item_map -> {
//                    view?.findNavController()
//                        ?.navigate(R.id.navigation_map)
//                    true
//                }
//                R.id.navigation_quest -> {
//                    view?.findNavController()
//                        ?.navigate(R.id.questFragment)
//                    true
//                }
//                R.id.navigation_backpack -> {
//                    false
//                }
//                R.id.navigation_person -> {
//                    view?.findNavController()
//                        ?.navigate(R.id.personFragment)
//                    true
//                }
//                R.id.navigation_chat -> {
//                    view?.findNavController()?.navigate(R.id.chatFragment)
//                    true
//                }
//                else -> false
//            }
//        }
//    }

}