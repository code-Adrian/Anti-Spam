package com.ab.anti_spam.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ab.anti_spam.ui.callblacklist.CountryBlockViewPager
import com.ab.anti_spam.ui.callblacklist.NumberBlockViewPager
import com.ab.anti_spam.ui.callblacklist.RegexBlockViewPager


internal class CallBlacklistTablayoutAdapter(var context: Context,var fm: FragmentManager,var totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                CountryBlockViewPager()
            }
            1 -> {
                NumberBlockViewPager()
            }
            2 -> {
                RegexBlockViewPager()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }



}