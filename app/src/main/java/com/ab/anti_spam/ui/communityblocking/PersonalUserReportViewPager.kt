package com.ab.anti_spam.ui.communityblocking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.FragmentCommunityUserReportViewPagerBinding
import com.ab.anti_spam.databinding.FragmentPersonalUserReportViewPagerBinding
import com.ab.anti_spam.main.Main


class PersonalUserReportViewPager : Fragment() {

    lateinit var app: Main
    private var _fragBinding: FragmentPersonalUserReportViewPagerBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val blacklistViewModel: CommunityblockingViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentPersonalUserReportViewPagerBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        return root
    }

}