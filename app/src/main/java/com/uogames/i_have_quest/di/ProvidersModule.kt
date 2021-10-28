package com.uogames.i_have_quest.di

import android.app.Application
import android.app.GameManager
import android.content.Context
import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ProvidersModule {

    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    fun provideViewModelFactory(creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory =
        ViewModelFactory(creators)


    @Provides
    fun provideGameProvider(context: Context): GameProvider =
        GameProvider.getINSTANCE(context)

}