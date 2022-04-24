package com.uogames.i_have_quest.ui.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BottomNavigationViewModel : ViewModel() {

	private val _selectedItemID = MutableStateFlow(-1)
	val selectedItemID = _selectedItemID.asStateFlow()

	fun setItemID(id: Int): Boolean {
		val prev = _selectedItemID.value
		_selectedItemID.value = id
		return prev != id
	}


}