package com.ab.anti_spam.ui.smsblacklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.FragmentCallblacklistBinding
import com.ab.anti_spam.databinding.FragmentSmsblacklistBinding
import com.ab.anti_spam.main.Main

class Smsblacklist : Fragment() {

    lateinit var app: Main
    private var _fragBinding: FragmentSmsblacklistBinding? = null
    private val fragBinding get() = _fragBinding!!

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

        return root
    }


}