package com.ab.anti_spam.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ab.anti_spam.ui.communityblocking.CommunityUserReportViewPager
import com.ab.anti_spam.ui.communityblocking.PersonalUserReportViewPager

class CommunityBlockingTablayoutAdapter(var context: Context, var fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> CommunityUserReportViewPager()

            1 -> PersonalUserReportViewPager()

            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}