package com.uogames.i_have_quest.ui.person

import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PersonViewModel @Inject constructor(private val game: GameProvider) : ViewModel() {

	private val ioScope = CoroutineScope(Dispatchers.IO)

	private val _title = MutableStateFlow("")
	val title: StateFlow<CharSequence> = _title.asStateFlow()

	private val _username = MutableStateFlow("")
	val username: StateFlow<CharSequence> = _username.asStateFlow()

	private val _lvl = MutableStateFlow("1")
	val lvl: StateFlow<CharSequence> = _lvl.asStateFlow()

	private val _force = MutableStateFlow("0")
	val force: StateFlow<CharSequence> = _force.asStateFlow()

	private val _defence = MutableStateFlow("0")
	val defence: StateFlow<CharSequence> = _defence.asStateFlow()

	private val _agility = MutableStateFlow("0")
	val agility: StateFlow<CharSequence> = _agility.asStateFlow()

	private val _vitality = MutableStateFlow("0")
	val vitality: StateFlow<CharSequence> = _vitality.asStateFlow()

	private val _health = MutableStateFlow("0")
	val health: StateFlow<CharSequence> = _health.asStateFlow()

	private val _maxHealth = MutableStateFlow("0")
	val maxHealth: StateFlow<CharSequence> = _maxHealth.asStateFlow()

	private val _experience = MutableStateFlow("0")
	val experience: StateFlow<CharSequence> = _experience.asStateFlow()

	init {
		val calcMaxHealth: suspend (Any) -> Unit = {
			var health = 0L
			health = _vitality.value.toLong() * 10
			_maxHealth.value = health.toString()
		}
		_vitality.onEach(calcMaxHealth).launchIn(ioScope)
	}

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