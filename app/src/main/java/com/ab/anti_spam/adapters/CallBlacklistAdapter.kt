package com.ab.anti_spam.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ab.anti_spam.models.CallBlacklistModel
import com.ab.anti_spam.databinding.CallblacklistCardBinding

interface deleteListener{
    fun onDeleteClick(model: CallBlacklistModel)
}

class CallBlacklistAdapter constructor(private var blacklist: MutableList<CallBlacklistModel>, private val deleteListener: deleteListener) : RecyclerView.Adapter<CallBlacklistAdapter.MainHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallBlacklistAdapter.MainHolder {
        val binding = CallblacklistCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }

    override fun getItemCount(): Int = blacklist.size

    fun remoteAt(position: Int) {
        blacklist.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: CallBlacklistAdapter.MainHolder, position: Int) {
        val blacklists = blacklist[holder.adapterPosition]
        holder.bind(blacklists,deleteListener)
    }

    inner class MainHolder(val binding: CallblacklistCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: CallBlacklistModel, deleteListener: deleteListener){


            binding.root.tag = model
            binding.callblacklist = model
            binding.remove.setOnClickListener{
                deleteListener.onDeleteClick(model)
            }
        }
    }

}