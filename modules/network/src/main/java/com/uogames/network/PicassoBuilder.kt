package com.uogames.network

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

object PicassoBuilder {

    fun get(context: Context): Picasso {
        val client = HttpClient.client
        val picasso = Picasso.Builder(context).downloader(OkHttp3Downloader(client)).build()
        if (BuildConfig.DEBUG)
            picasso.isLoggingEnabled = true
        return picasso
    }

}