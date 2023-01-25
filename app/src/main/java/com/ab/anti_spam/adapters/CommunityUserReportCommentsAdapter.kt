package com.ab.anti_spam.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ab.anti_spam.databinding.UserReportCardBinding
import com.ab.anti_spam.databinding.UserReportCommentsCardBinding
import com.ab.anti_spam.models.CommunityBlockingCommentsModel

interface cardCommentClickListener{
    fun onCardClick(model: CommunityBlockingCommentsModel)
}

class CommunityUserReportCommentsAdapter constructor(private var userReportCommentsModel: MutableList<CommunityBlockingCommentsModel>,private val cardClickListener: cardCommentClickListener) : RecyclerView.Adapter<CommunityUserReportCommentsAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityUserReportCommentsAdapter.MainHolder {
        val binding = UserReportCommentsCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }


    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val userReportCommentsModels = userReportCommentsModel[holder.adapterPosition]
        holder.bind(userReportCommentsModels,cardClickListener)
    }

    override fun getItemCount(): Int = userReportCommentsModel.size

    inner class MainHolder(val binding: UserReportCommentsCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: CommunityBlockingCommentsModel, cardClickListener: cardCommentClickListener){

            binding.root.tag = model
            binding.communitymodel = model

            //TODO
            binding.userReportCommentCard.setOnClickListener{cardClickListener.onCardClick(model)}
        }
    }


}