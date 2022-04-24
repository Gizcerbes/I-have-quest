package com.uogames.i_have_quest.di

import androidx.lifecycle.ViewModel
import com.uogames.i_have_quest.models.LocationModel
import com.uogames.i_have_quest.models.PermissionModel
import com.uogames.i_have_quest.ui.login.LoginViewModel
import com.uogames.i_have_quest.ui.maps.MapViewModel
import com.uogames.i_have_quest.ui.person.PersonViewModel
import com.uogames.i_have_quest.ui.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(PermissionModel::class)
	abstract fun bindPermissionModel(viewModel: PermissionModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(LoginViewModel::class)
	abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(RegistrationViewModel::class)
	abstract fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(PersonViewModel::class)
	abstract fun bindPersonViewModel(viewModel: PersonViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(LocationModel::class)
	abstract fun bindLocationModel(viewModel: LocationModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(MapViewModel::class)
	abstract fun bindMapViewModel(viewModel: MapViewModel): ViewModel

}