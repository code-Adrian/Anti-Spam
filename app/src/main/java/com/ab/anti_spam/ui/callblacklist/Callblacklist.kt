package com.ab.anti_spam.ui.callblacklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ab.anti_spam.databinding.FragmentCallblacklistBinding


class callblacklist : Fragment() {

    private var _binding: FragmentCallblacklistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val callblacklistViewModel =
            ViewModelProvider(this).get(CallblacklistViewModel::class.java)

        _binding = FragmentCallblacklistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}