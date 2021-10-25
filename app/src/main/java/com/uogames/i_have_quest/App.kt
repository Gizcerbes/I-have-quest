package com.uogames.i_have_quest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App :DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return AndroidInjector {  }
    }
}