package com.ab.anti_spam.ui.communityblocking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ab.anti_spam.R
import com.ab.anti_spam.adapters.CommunityUserReportAdapter
import com.ab.anti_spam.adapters.CommunityUserReportCommentsAdapter
import com.ab.anti_spam.adapters.cardClickListener
import com.ab.anti_spam.adapters.cardCommentClickListener
import com.ab.anti_spam.databinding.FragmentCommunityUserReportViewPagerBinding
import com.ab.anti_spam.databinding.FragmentPersonalUserReportViewPagerBinding
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.CommunityBlockingCommentsModel
import com.ab.anti_spam.models.CommunityBlockingModel
import com.ab.anti_spam.ui.auth.LoggedInViewModel


class PersonalUserReportViewPager : Fragment(), cardClickListener {

    lateinit var app: Main
    private var _fragBinding: FragmentPersonalUserReportViewPagerBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val communityViewModel: CommunityblockingViewModel by activityViewModels()
    lateinit var adapter: CommunityUserReportAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentPersonalUserReportViewPagerBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        loadUserAndReports()
        return root
    }

    private fun loadUserAndReports(){
        communityViewModel.observableUID.observe(viewLifecycleOwner,{uid ->
            uid?.let {
                loadReports(uid.toString())
            }
        })
    }

    private fun renderRecyclerView(model: ArrayList<CommunityBlockingModel>){
        fragBinding.personalCommunityRecyclerview.layoutManager = LinearLayoutManager(activity)
        fragBinding.personalCommunityRecyclerview.adapter = CommunityUserReportAdapter(model as ArrayList<CommunityBlockingModel>,this)
        adapter = fragBinding.personalCommunityRecyclerview.adapter as CommunityUserReportAdapter
    }


    private fun loadReports(uid: String){
        fragBinding.personalCommunityRecyclerview.adapter?.notifyDataSetChanged()
        communityViewModel.getPersonalReports(uid)
        communityViewModel.observablePersonalReportList.observe(viewLifecycleOwner,{reports->
            reports?.let{
                renderRecyclerView(reports as ArrayList<CommunityBlockingModel>)
            }
        })
    }

    override fun onCardClick(model: CommunityBlockingModel) {
        TODO("Not yet implemented")
    }

}