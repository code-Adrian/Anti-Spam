package com.ab.anti_spam.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ab.anti_spam.databinding.ChooseNumberCardBinding
import com.ab.anti_spam.models.ChooseNumberModel
import com.github.mikephil.charting.utils.ColorTemplate

interface chooseNumberListener{
    fun onChoose(model: ChooseNumberModel)
}

class ReportChooseNumberAdapter constructor(private var recentlist: MutableList<ChooseNumberModel>, private val chooseNumberListener: chooseNumberListener): RecyclerView.Adapter<ReportChooseNumberAdapter.MainHolder>(){

    override fun getItemCount(): Int = recentlist.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportChooseNumberAdapter.MainHolder {
        val binding = ChooseNumberCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recentlists = recentlist[holder.adapterPosition]
        holder.bind(recentlists,chooseNumberListener)
    }


inner class MainHolder(val binding: ChooseNumberCardBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(model: ChooseNumberModel, chooseNumberListener: chooseNumberListener){
        binding.root.tag = model
        binding.choosemodel = model
        binding.chooseNumberCard.setOnClickListener{
            chooseNumberListener.onChoose(model)
        }

        println(binding.cachedName.text)
        if(model.cached_name.equals("Contact not saved")){
            binding.cachedName.setTextColor(ColorTemplate.COLORFUL_COLORS[0])
        }else{
            binding.cachedName.setTextColor(ColorTemplate.COLORFUL_COLORS[3])
        }

    }
}
}