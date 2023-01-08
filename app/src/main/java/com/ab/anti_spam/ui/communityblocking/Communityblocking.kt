package com.ab.anti_spam.ui.communityblocking

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ab.anti_spam.R

class Communityblocking : Fragment() {

    companion object {
        fun newInstance() = Communityblocking()
    }

    private lateinit var viewModel: CommunityblockingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_communityblocking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommunityblockingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}