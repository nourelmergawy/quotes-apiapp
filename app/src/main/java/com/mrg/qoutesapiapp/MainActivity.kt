package com.mrg.qoutesapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrg.qoutesapiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mResult: ArrayList<Result>
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: QuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        mResult = ArrayList()
        viewModel =  ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))
            .get(QuoteViewModel::class.java)
        viewModel.getQuote(applicationContext)
        viewModel.mutableLiveData.observe(this , Observer {
            for(i : Int in 0..it.results.size-1){
                mResult.add(it.results.get(i))
            }

            adapter = RecyclerViewAdapter(applicationContext, mResult)
            recyclerView.adapter = adapter

        })

//        GlobalScope.launch {
//            val result = quotesApi.getQuotes()
//            result.enqueue(object : Callback<List<QuoteList>> {
//                override fun onResponse(
//                    call: Call<List<QuoteList>>,
//                    response: Response<List<QuoteList>>
//                ) {
//                    Log.d("ayush: ", response.body()!!.size.toString())
//
////                    // Checking the result
//                    for (item in response.body()!!) {
////                        for (i: Int in 0..response.body()!!.size) {
//                            var obj = item.results
//                            mResult.add(obj.get(0))
//                        Log.d(TAG, "onResponse: i'm here")
//                            Log.d("ayush: ", mResult.get(0).content)
//
////                        }
//                    }
//
//
//                }
//
//                override fun onFailure(call: Call<List<QuoteList>>, t: Throwable) {
//                    Toast.makeText(this@MainActivity, "error", Toast.LENGTH_LONG)
//                }
//
//            })

        }


        fun setRecyclerView() {

             recyclerView = binding.recyclerview
            // setting grid layout manager to implement grid view.
            // in this method '2' represents number of columns to be displayed in grid view.
            val layoutManager =
                GridLayoutManager(applicationContext, 2)

            // at last set adapter to recycler view.
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(false)
            // This will pass the ArrayList to our Adapter
            // Setting the Adapter with the recyclerview
        }

}