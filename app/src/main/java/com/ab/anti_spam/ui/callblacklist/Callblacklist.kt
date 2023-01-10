package com.ab.anti_spam.ui.callblacklist

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.FragmentCallblacklistBinding
import com.ab.anti_spam.main.Main


class Callblacklist : Fragment() {
    lateinit var app: Main
    private var _fragBinding: FragmentCallblacklistBinding? = null

    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentCallblacklistBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        fragBinding.fab.setOnClickListener{
        val optionsDialog = OptionsDialog()

            optionsDialog.show(parentFragmentManager,"000")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}