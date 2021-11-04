package com.uogames.i_have_quest.di

import com.uogames.i_have_quest.ui.backpack.BackpackFragment
import com.uogames.i_have_quest.ui.login.LoginFragment
import com.uogames.i_have_quest.ui.person.PersonFragment
import com.uogames.i_have_quest.ui.registration.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

	@ContributesAndroidInjector
	abstract fun contributeLoginFragment(): LoginFragment

	@ContributesAndroidInjector
	abstract fun contributePersonFragment(): PersonFragment

	@ContributesAndroidInjector
	abstract fun contributeRegistrationFragment(): RegistrationFragment

	@ContributesAndroidInjector
	abstract fun contributeBackpackFragment(): BackpackFragment

}