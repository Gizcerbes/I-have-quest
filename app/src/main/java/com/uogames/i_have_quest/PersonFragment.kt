package com.uogames.i_have_quest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class PersonFragment : Fragment() {

    private val navigationMenu by lazy { view?.findViewById<BottomNavigationView>(R.id.bottom_navi) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigationMenu()
    }

    private fun initNavigationMenu() {
        navigationMenu?.selectedItemId = R.id.item_person
        navigationMenu?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_map -> {
                    view?.findNavController()?.navigate(R.id.action_personFragment_to_mapsFragment)
                    true
                }
                R.id.item_quest -> {
                    view?.findNavController()?.navigate(R.id.action_personFragment_to_questFragment)
                    true
                }
                R.id.item_basket -> {
                    view?.findNavController()?.navigate(R.id.action_personFragment_to_backpackFragment)
                    true
                }
                R.id.item_person -> {
                    false
                }
                else -> false
            }
        }
    }

}