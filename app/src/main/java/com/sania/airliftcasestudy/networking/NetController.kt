package com.sania.airliftcasestudy.networking

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetController {

    private var _retrofit: Retrofit? = null

    fun getRetrofit() : Retrofit
    {
        if (_retrofit == null) {
            var gson = GsonBuilder().setLenient().create()
            _retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return _retrofit!!
    }

    fun getHttpClient() : OkHttpClient
    {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return getRetrofit().create(service)
    }

}