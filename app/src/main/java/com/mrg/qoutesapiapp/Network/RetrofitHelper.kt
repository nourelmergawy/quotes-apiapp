package com.mrg.qoutesapiapp.Network

import android.content.Context
import com.mrg.qoutesapiapp.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val baseUrl = "https://quotable.io/"
      var retrofitHelper : RetrofitHelper? =null
    val quotesApi :QuotesApi
    init {
        val retrofit:Retrofit =  Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
        quotesApi = retrofit?.create(QuotesApi::class.java) !!

    }

    fun getInstance(context: Context): RetrofitHelper {
        if (retrofitHelper == null){
            retrofitHelper?: synchronized(this){
                val retrofitHelper = RetrofitHelper
                return retrofitHelper!!
            }
        }
        return retrofitHelper!!
    }

    fun getApi():QuotesApi{
        return quotesApi
    }
}