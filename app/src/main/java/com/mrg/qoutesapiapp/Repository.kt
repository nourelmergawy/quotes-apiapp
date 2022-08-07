package com.mrg.qoutesapiapp

import android.content.Context
import com.mrg.qoutesapiapp.Network.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call

class Repository {
   companion object{
       private var INSTANCE :Repository?= null
       fun getInstance(context: Context):Repository{
           if (INSTANCE == null){
               INSTANCE?: synchronized(this){
                   val INSTANCE = Repository
                   return Companion.INSTANCE!!
               }

           }
           return INSTANCE!!
       }
   }
    suspend fun getQuote(context: Context): Call<QuoteList> {
      val call = RetrofitHelper.getInstance(context)?.getApi().getQuotes()!!
      return call!!
    }
}