package com.uogames.i_have_quest.di

import androidx.lifecycle.ViewModel
import com.uogames.i_have_quest.ui.login.LoginViewModel
import com.uogames.i_have_quest.ui.person.PersonViewModel
import com.uogames.i_have_quest.ui.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(LoginViewModel::class)
	abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(RegistrationViewModel::class)
	abstract fun bindRegistrationViewModel(loginViewModel: RegistrationViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(PersonViewModel::class)
	abstract fun bindPersonViewModel(loginViewModel: PersonViewModel): ViewModel

}