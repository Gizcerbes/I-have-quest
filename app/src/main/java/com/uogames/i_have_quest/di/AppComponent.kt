package com.example.myapplicationdagger.di

import android.app.Application
import com.example.myapplicationdagger.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(app: Application): Builder

        fun build(): AppComponent
    }
}