package com.uogames.network

import android.os.Build
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

object HttpClient {
    private val trusts = createTrustAllCerts()
    //private val cookie = createCookieJar()

    val client = getUnsafeOkHttpClient()


    private fun getUnsafeOkHttpClient(): OkHttpClient {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trusts, SecureRandom())
        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trusts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            //.cookieJar(cookie)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG)
                    level = HttpLoggingInterceptor.Level.BODY
            })
            .addNetworkInterceptor{chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("model", Build.MODEL)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    private fun createCookieJar() :CookieJar = object : CookieJar {
        var cookies: List<Cookie> = ArrayList()

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            return cookies
        }
        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            this.cookies = cookies
        }
    }

    private fun createTrustAllCerts(): Array<TrustManager> = arrayOf(object : X509TrustManager {
        override fun checkClientTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?
        ) {
        }

        override fun checkServerTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?
        ){
        }

        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    })



}