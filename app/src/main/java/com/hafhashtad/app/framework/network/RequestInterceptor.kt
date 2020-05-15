package com.hafhashtad.app.framework.network

import com.hafhashtad.app.Const
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url.newBuilder()
            //.addQueryParameter(API_KEY_QUERY, API_KEY_VALUE)
            //.addQueryParameter(LANGUAGE_QUERY, LANGUAGE_VALUE)
            .build()

        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }

    companion object {
        const val API_KEY_QUERY = "api_key"
        const val API_KEY_VALUE = Const.API_KEY
        const val LANGUAGE_QUERY = "language"
        const val LANGUAGE_VALUE = Const.LANGUAGE
    }
}
