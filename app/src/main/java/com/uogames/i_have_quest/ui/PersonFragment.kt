package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uogames.data.entities.objectData.CharacteristicsObjectData
import com.uogames.data.entities.objectData.PersonObjectData
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentPersonBinding
import com.uogames.i_have_quest.models.NetworkModel
import com.uogames.networking.PicassoBuilder

class PersonFragment : Fragment() {

    companion object {
        const val PERSON_NAME = "PERSON_NAME"
        const val PERSON_ID = "PERSON_ID"
    }

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
        val personID = arguments?.getInt(PERSON_ID) ?: 0
        val myPersonID = networkModel.loginData.value?.person?.id
        if (personID == 0 || personID == myPersonID?.toInt()) {
            binding.btnSendMessage.visibility = View.GONE
            initObservers()
        } else {
            networkModel.getPersonById(personID) {
                it.person?.let { it1 -> setPersonInfo(it1) }
            }
            networkModel.getCharacteristicsById(personID) {
                setCharacteristics(it)
            }
        }
        networkModel.updateMyPerson()
        networkModel.updateCharacteristics()

    }

    private fun setPersonInfo(personData: PersonObjectData) {
        binding.tvExperience.text =
            personData.experience.toString() + "/" + personData.nextLvl.toString()
        binding.tvTitle.text = personData.title
        binding.tvName.text = personData.personName
        binding.tvLvl.text = personData.lvl.toString()
        try {
            PicassoBuilder.get(requireContext()).load(getString(R.string.link_image_server) + personData.image).into(binding.ivPhoto)
            Log.e("TAG", getString(R.string.link_image_server) + personData.image.toString())
        } catch (e: Throwable) {
            Log.e("TAG", e.message.toString())
        }
        binding.btnSendMessage.setOnClickListener {
            val personID = personData.id.toInt()
            view?.findNavController()?.navigate(
                R.id.chatMessagesFragment,
                Bundle().apply { putInt(ChatMessagesFragment.ID_RECEIVER_KEY, personID) })
        }
    }

    private fun setCharacteristics(characteristicsObjectData: CharacteristicsObjectData) {
        binding.tvForce.text = characteristicsObjectData.force.toString()
        binding.tvDefence.text = characteristicsObjectData.defence.toString()
        binding.tvAgility.text = characteristicsObjectData.agility.toString()
        binding.tvHealth.text = characteristicsObjectData.health.toString()
    }

    private fun initObservers() {

        networkModel.personData.observe(requireActivity()) {
            setPersonInfo(it)
        }
        networkModel.characteristicsData.observe(requireActivity()) {
            setCharacteristics(it)
        }
    }
}