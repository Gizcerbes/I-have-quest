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
        //initNavigationMenu()
    }

    fun initObservers() {
        networkModel.personData.observe(requireActivity()) {
            if (it == null) return@observe
            binding.tvExperience.text = it.experience.toString() + "/" + it.nextLvl.toString()
            binding.tvTitle.text = it.title
            binding.tvName.text = it.personName
            binding.tvLvl.text = it.lvl.toString()
            try {
                Glide.with(requireActivity())
                    .load(getString(R.string.link_image_server) + it.image)
                    .diskCacheStrategy(
                        DiskCacheStrategy.NONE
                    ).skipMemoryCache(true).into(binding.ivPhoto)
            } catch (e: Throwable) {

            }
        }
        networkModel.characteristicsData.observe(requireActivity()) {
            if (it == null) return@observe
            binding.tvForce.text = it.force.toString()
            binding.tvDefence.text = it.defence.toString()
            binding.tvAgility.text = it.agility.toString()
            binding.tvHealth.text = it.health.toString()
        }
    }

}