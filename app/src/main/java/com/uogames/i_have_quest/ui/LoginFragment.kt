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
import com.uogames.i_have_quest.databinding.FragmentLoginBinding
import com.uogames.i_have_quest.models.NetworkModel
import com.uogames.i_have_quest.utils.checkLength

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val networkModel by lazy { ViewModelProvider(requireActivity()).get(NetworkModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initButtons()
    }

    private fun initListeners() {
        networkModel.loginData.observe(requireActivity()) {
            if (it.status?.statusCode == 200) {
               findNavController().navigate(R.id.mapsFragment)
            } else {
                MaterialAlertDialogBuilder(this.requireContext())
                    .setTitle("LogIn error")
                    .setMessage(it.status?.message)
                    .setPositiveButton("Ok") { _, _ -> }
                    .show()
            }
        }
    }

    private fun initButtons() {
        binding.btnRegistration.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }
        binding.btnLogIn.setOnClickListener {
            val login = binding.tiUsername.editText?.text.toString()
            val password = binding.tiPassword.editText?.text.toString()
            if (binding.tiUsername.checkLength(
                    min = 3,
                    pattern = getString(R.string.exception_longer)
                )
                && binding.tiPassword.checkLength(
                    min = 3,
                    pattern = getString(R.string.exception_longer)
                )
            ) {
                networkModel.logIn(login, password)
            }
        }
    }

}