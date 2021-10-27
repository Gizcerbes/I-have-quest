package com.uogames.i_have_quest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
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


    }
}