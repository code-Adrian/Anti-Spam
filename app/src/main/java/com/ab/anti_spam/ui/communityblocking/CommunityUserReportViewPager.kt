package com.ab.anti_spam.ui.communityblocking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ab.anti_spam.R
import com.ab.anti_spam.adapters.CallBlacklistAdapter
import com.ab.anti_spam.adapters.CommunityUserReportAdapter
import com.ab.anti_spam.adapters.cardClickListener
import com.ab.anti_spam.databinding.FragmentCommunityUserReportViewPagerBinding
import com.ab.anti_spam.databinding.FragmentCommunityblockingBinding
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.CommunityBlockingModel

class CommunityUserReportViewPager : Fragment(),cardClickListener {

    lateinit var app: Main
    private var _fragBinding: FragmentCommunityUserReportViewPagerBinding? = null
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
    ): View? {

        _fragBinding = FragmentCommunityUserReportViewPagerBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        loadReports()

        return root
    }

    private fun renderRecyclerView(model: ArrayList<CommunityBlockingModel>){
        fragBinding.communityRecyclerview.layoutManager = LinearLayoutManager(activity)
        fragBinding.communityRecyclerview.adapter = CommunityUserReportAdapter(model as ArrayList<CommunityBlockingModel>,this)
        adapter = fragBinding.communityRecyclerview.adapter as CommunityUserReportAdapter
    }

    private fun loadReports(){
        fragBinding.communityRecyclerview.adapter?.notifyDataSetChanged()
        communityViewModel.getRecent100UserReports()
        communityViewModel.observableCommunityReportList.observe(viewLifecycleOwner,{reports->
            reports?.let {
                renderRecyclerView(reports as ArrayList<CommunityBlockingModel>)
            }
        })
    }

    override fun onCardClick(model: CommunityBlockingModel) {
        val bundle = Bundle()
        bundle.putParcelable("model",model)
        findNavController().navigate(R.id.action_nav_community_blocking_to_communityViewReport,bundle)
    }

}