package com.mrg.qoutesapiapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.mrg.qoutesapiapp.RecyclerViewAdapter.*
import com.mrg.qoutesapiapp.databinding.QuoteItemBinding

class RecyclerViewAdapter(private val context: Context, private val mResult: ArrayList<Result>)
    : RecyclerView.Adapter<RecyclerViewHolder>() {
    lateinit var binding: QuoteItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        binding = QuoteItemBinding.inflate(LayoutInflater.from(parent.context))
        return  RecyclerViewHolder(binding)
    }
    class RecyclerViewHolder (binding :QuoteItemBinding ) : RecyclerView.ViewHolder(binding.root){
        val quotetv :TextView
        val authortv : TextView
        val cardView : CardView
        init {
            quotetv = binding.quoteBody
            authortv = binding.authorTv
            cardView = binding.quotesContainer
        }

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.quotetv.setText(mResult.get(position).content)
        holder.authortv.setText(mResult.get(position).author)
        holder.cardView.setOnClickListener(View.OnClickListener {
            val clip: ClipData = ClipData.newPlainText("text",holder.quotetv.text)

        })
    }

    override fun getItemCount(): Int {
       return mResult.size
    }


}
