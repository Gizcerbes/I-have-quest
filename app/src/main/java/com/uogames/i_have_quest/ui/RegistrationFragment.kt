package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.data.entities.RegistrationData
import com.uogames.i_have_quest.databinding.FragmentRegistrationBinding
import com.uogames.i_have_quest.models.NetworkModel
import com.uogames.i_have_quest.utils.checkLength

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val networkModel by lazy { ViewModelProvider(requireActivity()).get(NetworkModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons(view)
    }

    private fun initButtons(view: View) {
        binding.btnRegistration.setOnClickListener {
            val login = binding.tiUsername.editText?.text.toString()
            val password = binding.tiPassword.editText?.text.toString()
            val repeatPassword = binding.tiPasswordRepeat.editText?.text.toString()
            val checkLogin = binding.tiUsername.checkLength(
                min = 3,
                pattern = getString(R.string.exception_longer)
            )
            val checkPassword = binding.tiPassword.checkLength(
                min = 3,
                pattern = getString(R.string.exception_longer)
            )
            binding.tiPasswordRepeat.checkLength(
                min = 3,
                pattern = getString(R.string.exception_longer)
            )

            if (password != repeatPassword) {
                MaterialAlertDialogBuilder(this.requireContext())
                    .setTitle("Registration error")
                    .setMessage("Password mismatch")
                    .setPositiveButton("Ok") { _, _ -> }
                    .show()
            } else if (checkLogin && checkPassword) {
                networkModel.register(login, password) { initListeners(view, it) }
            }
        }
    }


    private fun initListeners(view: View, registerData: RegistrationData) {
        registerData.let {
            if (it.status?.statusCode == 201) {
                MaterialAlertDialogBuilder(this.requireContext())
                    .setTitle("Registration Ok")
                    .setMessage(it.status.message)
                    .setPositiveButton("Ok") { _, _ ->
                        findNavController().navigateUp()
                    }
                    .show()
            } else {
                MaterialAlertDialogBuilder(this.requireContext())
                    .setTitle("Registration error")
                    .setMessage(it.status?.message)
                    .setPositiveButton("Ok") { _, _ -> }
                    .show()
            }
        }
    }

}