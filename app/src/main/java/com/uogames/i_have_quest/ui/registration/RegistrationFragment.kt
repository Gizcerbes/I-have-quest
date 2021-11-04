package com.uogames.i_have_quest.ui.registration

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentRegistrationBinding
import com.uogames.i_have_quest.di.ViewModelFactory
import com.uogames.i_have_quest.ui.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegistrationFragment : DaggerFragment() {

	private lateinit var bind: FragmentRegistrationBinding

	@Inject
	lateinit var factory: ViewModelFactory
	private val model by lazy {
		ViewModelProvider(requireActivity(), factory).get(RegistrationViewModel::class.java)
	}


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		bind = FragmentRegistrationBinding.inflate(inflater, container, false)
		return bind.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		Log.e("TAG", requireActivity().toString())

		bind.tiUsername.editText?.setText(model.username.value)
		bind.tiPassword.editText?.setText(model.password.value)
		bind.tiPasswordRepeat.editText?.setText(model.repeatPassword.value)

		bind.tiUsername.editText?.addTextChangedListener { model.setUsername(it.toString()) }
		bind.tiPassword.editText?.addTextChangedListener { model.setPassword(it.toString()) }
		bind.tiPasswordRepeat.editText?.addTextChangedListener { model.setRepeatPassword(it.toString()) }

		model.usernameErrorCode.onEach {
			when (it) {
				RegistrationViewModel.Errors.SHORT ->
					bind.tiUsername.error = context?.getString(R.string.error_username_short)
				RegistrationViewModel.Errors.LONG ->
					bind.tiUsername.error = context?.getString(R.string.errors_username_long)
				else -> bind.tiUsername.error = null
			}
		}.launchIn(lifecycleScope)

		model.passwordErrorCode.onEach {
			when (it) {
				RegistrationViewModel.Errors.SHORT ->
					bind.tiPassword.error = context?.getString(R.string.errors_password_short)
				RegistrationViewModel.Errors.LONG ->
					bind.tiPassword.error = context?.getString(R.string.errors_password_long)
				else -> bind.tiPassword.error = null
			}
		}.launchIn(lifecycleScope)

		model.repeatErrorCode.onEach {
			when (it) {
				RegistrationViewModel.Errors.NOT_EQUALS ->
					bind.tiPasswordRepeat.error = context?.getString(R.string.password_mismatch)
				else -> bind.tiPasswordRepeat.error = null
			}
		}.launchIn(lifecycleScope)

		model.busy.onEach {
			bind.lpiProgress.visibility = if (it) View.VISIBLE else View.GONE
			bind.btnRegistration.isEnabled = !it
			bind.tiUsername.isEnabled = !it
			bind.tiPassword.isEnabled = !it
			bind.tiPasswordRepeat.isEnabled = !it
		}.launchIn(lifecycleScope)

		bind.topAppBar.setNavigationOnClickListener { findNavController().popBackStack() }

		bind.btnRegistration.setOnClickListener {
			model.registration { message, code ->
				when (code) {
					200 -> findNavController().navigate(R.id.to_bottomNavigationFragment)
					else -> Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
				}

			}
		}

	}

}