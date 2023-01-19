package com.ab.anti_spam.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.SmsblacklistCardBinding
import com.ab.anti_spam.models.CallBlacklistModel
import com.ab.anti_spam.models.SMSBlacklistModel

interface deleteListenerSMS{
    fun onDeleteClick(model: SMSBlacklistModel)
}

class SMSBlacklistAdapter constructor(private var blacklist: MutableList<SMSBlacklistModel>, private val deleteListener: deleteListenerSMS) : RecyclerView.Adapter<SMSBlacklistAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = SmsblacklistCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val blacklists = blacklist[holder.adapterPosition]
        holder.bind(blacklists,deleteListener)
    }

    override fun getItemCount(): Int = blacklist.size

    inner class MainHolder(val binding: SmsblacklistCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: SMSBlacklistModel, deleteListener: deleteListenerSMS){


            binding.root.tag = model
            binding.smsblacklist = model

            if(model.by_regex.isNotEmpty()){
                binding.icon.setBackgroundResource(R.drawable.regex)
            }

            binding.remove.setOnClickListener{
                deleteListener.onDeleteClick(model)
            }
        }
    }


}