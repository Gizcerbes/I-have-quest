package com.uogames.i_has_quest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private val imgLogo by lazy { this.view?.findViewById<ImageView>(R.id.img_logo) }
    private val tiUserName by lazy { this.view?.findViewById<TextInputLayout>(R.id.ti_username) }
    private val tiPassword by lazy { this.view?.findViewById<TextInputLayout>(R.id.ti_password) }
    private val btnLogIn by lazy { this.view?.findViewById<Button>(R.id.btn_registration) }
    private val btnRegister by lazy { this.view?.findViewById<Button>(R.id.btn_registration) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}