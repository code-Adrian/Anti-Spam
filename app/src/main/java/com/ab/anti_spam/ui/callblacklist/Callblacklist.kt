package com.ab.anti_spam.ui.callblacklist

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ab.anti_spam.R
import com.ab.anti_spam.adapters.CallBlacklistAdapter
import com.ab.anti_spam.adapters.deleteListener
import com.ab.anti_spam.databinding.FragmentCallblacklistBinding
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.CallBlacklistModel


class Callblacklist : Fragment(),deleteListener {
    lateinit var app: Main
    private var _fragBinding: FragmentCallblacklistBinding? = null
    lateinit var adapter: CallBlacklistAdapter
    private val blacklistViewModel: CallblacklistViewModel by activityViewModels()
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _fragBinding = FragmentCallblacklistBinding.inflate(inflater, container, false)
        val root = fragBinding.root


        fragBinding.fab.setOnClickListener{
            val optionsDialog = OptionsDialog()
            optionsDialog.show(parentFragmentManager,null)
        }


        loadBlacklist()
        return root
    }

    private fun loadBlacklist(){
        fragBinding.callblacklistRecyclerview.adapter?.notifyDataSetChanged()
        blacklistViewModel.refresh(app)
        blacklistViewModel.observableBlacklist.observe(viewLifecycleOwner, Observer { blacklist ->
            blacklist?.let {
                renderRecyclerView(blacklist as ArrayList<CallBlacklistModel>)
            }
        })
    }

    private fun renderRecyclerView(model: ArrayList<CallBlacklistModel>){
        fragBinding.callblacklistRecyclerview.layoutManager = LinearLayoutManager(activity)
        fragBinding.callblacklistRecyclerview.adapter = CallBlacklistAdapter(model as ArrayList<CallBlacklistModel>,this)
        adapter = fragBinding.callblacklistRecyclerview.adapter as CallBlacklistAdapter
        if(app.localCallBlacklist.getAll().size > 0){
        fragBinding.blockIcon.isVisible = false
        fragBinding.blockText.isVisible = false
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onDeleteClick(model: CallBlacklistModel) {
        blacklistViewModel.deleteModel(model,app)
    }
}