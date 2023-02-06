package com.ab.anti_spam.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.UserReportCardBinding
import com.ab.anti_spam.databinding.UserReportCommentsCardBinding
import com.ab.anti_spam.models.CommunityBlockingCommentsModel
import com.ab.anti_spam.models.CommunityBlockingModel
import com.github.mikephil.charting.utils.ColorTemplate

interface cardCommentClickListener{
    fun onCardClick(model: CommunityBlockingCommentsModel)
}
interface deleteCommentClickListener{
    fun onDeleteClick(model:CommunityBlockingCommentsModel)
}

class CommunityUserReportCommentsAdapter constructor(private var userReportCommentsModel: MutableList<CommunityBlockingCommentsModel>,private val cardClickListener: cardCommentClickListener,private val onDeleteClickListener : deleteCommentClickListener,private val currentUID: String) : RecyclerView.Adapter<CommunityUserReportCommentsAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityUserReportCommentsAdapter.MainHolder {
        val binding = UserReportCommentsCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }


    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val userReportCommentsModels = userReportCommentsModel[holder.adapterPosition]
        holder.bind(userReportCommentsModels,cardClickListener,onDeleteClickListener,currentUID)
    }

    override fun getItemCount(): Int = userReportCommentsModel.size

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(model: CommunityBlockingCommentsModel){
        this.userReportCommentsModel.remove(model)
        notifyDataSetChanged()
    }

    inner class MainHolder(val binding: UserReportCommentsCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: CommunityBlockingCommentsModel, cardClickListener: cardCommentClickListener,onDeleteClickListener : deleteCommentClickListener,currentUID: String){

            binding.root.tag = model
            binding.communitymodel = model

            binding.deleteComment.isEnabled = false
            binding.deleteComment.visibility = View.INVISIBLE

            binding.userReportCommentCard.setOnClickListener{cardClickListener.onCardClick(model)}

            if(model.risk_Level.equals("High")){
                binding.root.setBackgroundResource(R.drawable.icon_gradient_high)
                binding.riskLevel.setTextColor(Color.RED)
            }
            if(model.risk_Level.equals("Medium")){
                binding.riskLevel.setTextColor(ColorTemplate.COLORFUL_COLORS[1])
                binding.root.setBackgroundResource(R.drawable.icon_gradient_medium)
            }
            if(model.risk_Level.equals("Low")){
                binding.root.setBackgroundResource(R.drawable.icon_gradient_low)
            }

            if(currentUID.trim().equals(model.user_Id_comment.trim())){
                binding.deleteComment.isEnabled = true
                binding.deleteComment.visibility = View.VISIBLE
                binding.deleteComment.setOnClickListener{onDeleteClickListener.onDeleteClick(model)}
            }

            binding.commentDescription.text = shortenComment(model.comment_Description)

        }

        fun shortenComment(comment: String): String{
            return if(comment.length > 25){
                "${comment.substring(0,23)}...."
            }else{
                comment
            }
        }
    }


}