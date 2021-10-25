package com.uogames.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class Provider {
    protected val ioScope = CoroutineScope(Dispatchers.IO)
    protected val mainScope = CoroutineScope(Dispatchers.Main)
}