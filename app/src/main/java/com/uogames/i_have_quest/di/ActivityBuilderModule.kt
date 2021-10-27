package com.uogames.i_have_quest.di

import com.uogames.i_have_quest.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ProvidersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}