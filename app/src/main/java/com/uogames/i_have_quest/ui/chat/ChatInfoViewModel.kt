package com.uogames.i_have_quest.ui.chat

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatInfoViewModel : ViewModel() {


	private val _selectedItem = MutableStateFlow(0)
	val selectedItem = _selectedItem.asStateFlow()

	fun setSelectedItem(itemID: Int) {
		_selectedItem.value = itemID
	}

}