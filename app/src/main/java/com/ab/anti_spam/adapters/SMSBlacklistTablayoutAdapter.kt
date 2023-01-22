package com.ab.anti_spam.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ab.anti_spam.ui.smsblacklist.SMSKeywordBlockViewPager
import com.ab.anti_spam.ui.smsblacklist.SMSRegexBlockViewPager

internal class SMSBlacklistTablayoutAdapter(var context: Context, var fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                SMSKeywordBlockViewPager()
            }
            1 -> {
                SMSRegexBlockViewPager()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }



}