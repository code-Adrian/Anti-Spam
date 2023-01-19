package com.ab.anti_spam.ui.smsblacklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.ab.anti_spam.R
import com.ab.anti_spam.adapters.CallBlacklistTablayoutAdapter
import com.ab.anti_spam.adapters.SMSBlacklistTablayoutAdapter
import com.ab.anti_spam.databinding.FragmentCallblacklistBinding
import com.ab.anti_spam.databinding.FragmentSmsblacklistBinding
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.ui.callblacklist.CallblacklistViewModel
import com.ab.anti_spam.ui.callblacklist.OptionsDialog
import com.google.android.material.tabs.TabLayout

class Smsblacklist : Fragment() {

    lateinit var app: Main
    private var _fragBinding: FragmentSmsblacklistBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val blacklistViewModel: SmsblacklistViewModel by activityViewModels()

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentSmsblacklistBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        tabLayout = fragBinding.tabLayout
        viewPager = fragBinding.viewPager

        fragBinding.fab.setOnClickListener{
            val optionsDialog = SMSOptionsDialog()
            optionsDialog.show(parentFragmentManager,null)
        }

        emptyStorageLayout()
        observer()
        tabLayoutSetup()

        return root
    }


    private fun observer(){
        blacklistViewModel.refresh(app)
        blacklistViewModel.observableBlacklist.observe(viewLifecycleOwner, Observer { blacklist ->
            blacklist?.let {
                emptyStorageLayout()
            }
        })
    }

    private fun emptyStorageLayout(){
        if(app.localSMSBlacklist.getAll().size > 0){
            fragBinding.blockIcon.isVisible = false
            fragBinding.blockText.isVisible = false
        }else{
            fragBinding.blockIcon.isVisible = true
            fragBinding.blockText.isVisible = true
        }
    }

    private fun tabLayoutSetup(){
        tabLayout.removeAllTabs()
        tabLayout.addTab(tabLayout.newTab().setText("Keywords"))
        tabLayout.addTab(tabLayout.newTab().setText("Regex"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        viewPager.adapter = SMSBlacklistTablayoutAdapter(app.applicationContext,childFragmentManager,tabLayout.tabCount)

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