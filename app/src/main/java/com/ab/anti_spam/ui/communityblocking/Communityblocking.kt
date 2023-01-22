package com.ab.anti_spam.ui.communityblocking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.ab.anti_spam.adapters.CommunityBlockingTablayoutAdapter
import com.ab.anti_spam.databinding.FragmentCommunityblockingBinding
import com.ab.anti_spam.main.Main
import com.google.android.material.tabs.TabLayout

class Communityblocking : Fragment() {

    lateinit var app: Main
    private var _fragBinding: FragmentCommunityblockingBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val blacklistViewModel: CommunityblockingViewModel by activityViewModels()

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentCommunityblockingBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        tabLayout = fragBinding.tabLayout
        viewPager = fragBinding.viewPager

        tabLayoutSetup()

        return root
    }

    private fun tabLayoutSetup(){
        tabLayout.removeAllTabs()
        tabLayout.addTab(tabLayout.newTab().setText("Community Reports"))
        tabLayout.addTab(tabLayout.newTab().setText("My Reports"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        viewPager.adapter = CommunityBlockingTablayoutAdapter(app.applicationContext,childFragmentManager,tabLayout.tabCount)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }


}