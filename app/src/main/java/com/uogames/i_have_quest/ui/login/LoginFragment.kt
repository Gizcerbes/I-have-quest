package com.uogames.i_have_quest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentLoginBinding
import com.uogames.i_have_quest.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

	private lateinit var bind: FragmentLoginBinding

	@Inject
	lateinit var factory: ViewModelFactory
	private val model by lazy {
		ViewModelProvider(requireActivity(), factory).get(LoginViewModel::class.java)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentLoginBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		bind.tiUsername.editText?.setText(model.username.value)
		bind.tiUsername.editText?.addTextChangedListener() { model.setUsername(it.toString()) }
		bind.tiPassword.editText?.setText(model.password.value)
		bind.tiPassword.editText?.addTextChangedListener { model.setPassword(it.toString()) }

		model.usernameError.observe(requireActivity()) {
			when (it) {
				LoginViewModel.Errors.SHORT -> bind.tiUsername.error =
					getString(R.string.error_username_short)
				LoginViewModel.Errors.LONG -> bind.tiUsername.error =
					getString(R.string.errors_username_long)
				else -> bind.tiUsername.error = null
			}
		}
		model.passwordError.observe(requireActivity()) {
			when (it) {
				LoginViewModel.Errors.SHORT -> bind.tiPassword.error =
					getString(R.string.errors_password_short)
				LoginViewModel.Errors.LONG -> bind.tiPassword.error =
					getString(R.string.errors_password_long)
				else -> bind.tiPassword.error = null
			}
		}

		model.busy.observe(requireActivity()) {

		}

		bind.btnLogIn.setOnClickListener {
			model.login() {
				findNavController().navigate(R.id.to_bottomNavigationFragment)
			}
		}

		bind.btnRegistration.setOnClickListener {
			findNavController().navigate(R.id.to_registrationFragment)
		}

	}
}