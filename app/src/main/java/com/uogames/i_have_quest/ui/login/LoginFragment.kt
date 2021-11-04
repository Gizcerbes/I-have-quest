package com.uogames.i_have_quest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentLoginBinding
import com.uogames.i_have_quest.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
		bind.tiUsername.editText?.addTextChangedListener { model.setUsername(it.toString()) }
		bind.tiPassword.editText?.setText(model.password.value)
		bind.tiPassword.editText?.addTextChangedListener { model.setPassword(it.toString()) }

		model.usernameError.onEach {
			when (it) {
				LoginViewModel.Errors.SHORT ->
					bind.tiUsername.error = context?.getString(R.string.error_username_short)
				LoginViewModel.Errors.LONG ->
					bind.tiUsername.error = context?.getString(R.string.errors_username_long)
				else -> bind.tiUsername.error = null
			}
		}.launchIn(lifecycleScope)

		model.passwordError.onEach {
			when (it) {
				LoginViewModel.Errors.SHORT ->
					bind.tiPassword.error = context?.getString(R.string.errors_password_short)
				LoginViewModel.Errors.LONG ->
					bind.tiPassword.error = context?.getString(R.string.errors_password_long)
				else -> bind.tiPassword.error = null
			}
		}.launchIn(lifecycleScope)

		model.busy.onEach {
			bind.lpiProgress.visibility = if (it) View.VISIBLE else View.GONE
			bind.btnLogIn.isEnabled = !it
			bind.btnRegistration.isEnabled = !it
			bind.tiUsername.isEnabled = !it
			bind.tiPassword.isEnabled = !it
		}.launchIn(lifecycleScope)

		bind.btnLogIn.setOnClickListener {
			model.login() { message, code ->
				when (code) {
					200 -> findNavController().navigate(R.id.to_bottomNavigationFragment)
					else -> Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
				}
			}
		}

		bind.btnRegistration.setOnClickListener {
			findNavController().navigate(R.id.to_registrationFragment)
		}

	}
}