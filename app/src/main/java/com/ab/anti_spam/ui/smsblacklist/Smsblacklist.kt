package com.ab.anti_spam.ui.smsblacklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ab.anti_spam.R

class Smsblacklist : Fragment() {

    companion object {
        fun newInstance() = Smsblacklist()
    }

    private lateinit var viewModel: SmsblacklistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_smsblacklist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SmsblacklistViewModel::class.java)
        // TODO: Use the ViewModel
    }

}