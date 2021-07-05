package com.uogames.i_have_quest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class RegistrationFragment : Fragment() {

    private val imgLogo by lazy { view?.findViewById<ImageView>(R.id.img_logo) }
    private val welcomeText by lazy { view?.findViewById<TextView>(R.id.welcome_text) }
    private val tiUsername by lazy { view?.findViewById<TextInputLayout>(R.id.ti_username) }
    private val tiPassword by lazy { view?.findViewById<TextInputLayout>(R.id.ti_password) }
    private val tiPasswordRepeat by lazy { view?.findViewById<TextInputLayout>(R.id.ti_password_repeat) }
    private val btnRegistration by lazy { view?.findViewById<TextInputLayout>(R.id.btn_registration) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}