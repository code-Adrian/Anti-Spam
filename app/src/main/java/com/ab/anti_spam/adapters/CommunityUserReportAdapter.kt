package com.ab.anti_spam.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ab.anti_spam.databinding.SmsblacklistCardBinding
import com.ab.anti_spam.databinding.UserReportCardBinding
import com.ab.anti_spam.models.CommunityBlockingModel

interface cardClickListener{
    fun onCardClick(model: CommunityBlockingModel)
}

class CommunityUserReportAdapter constructor(private var userReportModel: MutableList<CommunityBlockingModel>,private val cardClickListener: cardClickListener) : RecyclerView.Adapter<CommunityUserReportAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityUserReportAdapter.MainHolder {
        val binding = UserReportCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }


    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val userReportModels = userReportModel[holder.adapterPosition]
        holder.bind(userReportModels,cardClickListener)
    }

    override fun getItemCount(): Int = userReportModel.size

inner class MainHolder(val binding: UserReportCardBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(model: CommunityBlockingModel,cardClickListener: cardClickListener){
        binding.root.tag = model
        binding.communitymodel = model

        //TODO
        binding.userReportCard.setOnClickListener{cardClickListener.onCardClick(model)}
    }
}


}