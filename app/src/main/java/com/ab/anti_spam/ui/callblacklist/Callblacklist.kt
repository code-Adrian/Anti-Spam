package com.ab.anti_spam.ui.callblacklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ab.anti_spam.databinding.FragmentCallblacklistBinding


class Callblacklist : Fragment() {

    private var _fragBinding: FragmentCallblacklistBinding? = null

    private val fragBinding get() = _fragBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentCallblacklistBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title  = "Personal Blacklist"

        fragBinding.fab.setOnClickListener{

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}