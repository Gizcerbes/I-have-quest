package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Picasso
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentPersonBinding
import com.uogames.i_have_quest.models.NetworkModel

class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private val networkModel by lazy { ViewModelProvider(requireActivity()).get(NetworkModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        networkModel.updateMyPerson()
        networkModel.updateCharacteristics()
        initNavigationMenu()
    }

    fun initObservers(){
        networkModel.personData.observe(requireActivity()) {
            if (it == null) return@observe
            binding.tvExperience.text = it.experience.toString() + "/" + it.nextLvl.toString()
            binding.tvTitle.text = it.title
            binding.tvName.text = it.personName
            binding.tvLvl.text = it.lvl.toString()
            Glide.with(this).load("http://192.169.0.101:8080/IHaveQuest/image?id=" + it.image)
                .diskCacheStrategy(
                    DiskCacheStrategy.NONE
                ).skipMemoryCache(true).into(binding.ivPhoto)
        }
        networkModel.characteristicsData.observe(requireActivity()){
            if (it == null) return@observe
            binding.tvForce.text = it.force.toString()
            binding.tvDefence.text = it.defence.toString()
            binding.tvAgility.text = it.agility.toString()
            binding.tvHealth.text = it.health.toString()
        }
    }

    private fun initNavigationMenu() {
        binding.bottomNavi.selectedItemId = R.id.item_person
        binding.bottomNavi.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_map -> {
                    view?.findNavController()?.navigate(R.id.mapsFragment)
                    true
                }
                R.id.item_quest -> {
                    view?.findNavController()?.navigate(R.id.questFragment)
                    true
                }
                R.id.item_basket -> {
                    view?.findNavController()?.navigate(R.id.backpackFragment)
                    true
                }
                R.id.item_person -> {
                    false
                }
                R.id.item_chat -> {
                    view?.findNavController()?.navigate(R.id.chatFragment)
                    true
                }
                else -> false
            }
        }
    }

}