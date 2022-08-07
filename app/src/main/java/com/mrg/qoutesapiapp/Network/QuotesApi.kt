package com.mrg.qoutesapiapp.Network

import com.mrg.qoutesapiapp.QuoteList
import retrofit2.Call
import retrofit2.http.GET

interface QuotesApi {
    @GET("quotes")
    fun getQuotes() : Call<QuoteList>

}