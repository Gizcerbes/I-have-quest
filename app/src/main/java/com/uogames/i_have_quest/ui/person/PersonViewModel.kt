package com.uogames.i_have_quest.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PersonViewModel @Inject constructor(private val game: GameProvider) : ViewModel() {

	private val _title = MutableStateFlow("")
	val title: StateFlow<CharSequence> = _title.asStateFlow()

	private val _username = MutableStateFlow("")
	val username: StateFlow<CharSequence> = _username.asStateFlow()

	private val _lvl = MutableStateFlow("")
	val lvl: StateFlow<CharSequence> = _lvl.asStateFlow()

	private val _force = MutableStateFlow("")
	val force: StateFlow<CharSequence> = _force.asStateFlow()

	private val _defence = MutableStateFlow("")
	val defence: StateFlow<CharSequence> = _defence.asStateFlow()

	private val _agility = MutableStateFlow("")
	val agility: StateFlow<CharSequence> = _agility.asStateFlow()

	private val _vitality = MutableStateFlow("")
	val vitality: StateFlow<CharSequence> = _vitality.asStateFlow()

	private val _health = MutableStateFlow("")
	val health: StateFlow<CharSequence> = _health.asStateFlow()

	private val _maxHealth = MutableStateFlow("")
	val maxHealth: StateFlow<CharSequence> = _maxHealth.asStateFlow()

	private val _experience = MutableStateFlow("")
	val experience: StateFlow<CharSequence> = _experience.asStateFlow()

	fun update() {
		game.getMyPerson {
			_username.value = it.name
			_title.value = it.title
		}
		game.getMyCharacteristic {
			_force.value = it.force.toString()
			_defence.value = it.defence.toString()
			_agility.value = it.agility.toString()
			_vitality.value = it.vitality.toString()
		}
		game.getMyExperience {
			_lvl.value = it.lvl.toString()
			_experience.value = it.experience.toString()
		}
		game.getMyHealth {
			_health.value = it.health.toString()
		}
	}

}