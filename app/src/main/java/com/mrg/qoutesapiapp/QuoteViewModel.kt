package com.mrg.qoutesapiapp

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteViewModel(application: Application) : AndroidViewModel(application) {
    var mutableLiveData : MutableLiveData<QuoteList>
    private var repository :Repository
    init {
        repository = Repository()
        mutableLiveData = MutableLiveData()
    }

    fun getQuote(context: Context) {
        viewModelScope.launch(Dispatchers.IO){
          var  call : Call<QuoteList> = repository.getQuote(context)
            call.enqueue(object : Callback<QuoteList> {
                override fun onResponse(
                    call: Call<QuoteList>,
                    response: Response<QuoteList>
                ) {
                    if (response.isSuccessful) {
                            mutableLiveData.postValue(response.body())
                        Log.d(TAG, "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t}")
                }
        })
    }
}
}