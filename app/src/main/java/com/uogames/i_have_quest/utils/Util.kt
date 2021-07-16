package com.uogames.i_have_quest.utils

import com.google.android.material.textfield.TextInputLayout

/**
 *  The string must have {min} and {max}
 */
fun TextInputLayout.checkLength(min: Int = 0, max: Int= counterMaxLength, pattern: String): Boolean {
    val text = editText?.text.toString()
    val maxText = counterMaxLength

    if (text.length in 3..maxText) {
        error = null
    } else {
        var exe = pattern.replace("{min}", "3")
        exe = exe.replace("{max}", maxText.toString())
        error = exe
    }
    return error == null
}