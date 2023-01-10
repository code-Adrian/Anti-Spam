package com.ab.anti_spam.ui.callblacklist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.CallblacklistCountryblockDialogBinding
import com.ab.anti_spam.databinding.CallblacklistOptionsDialogBinding

class CountryBlockDialog: DialogFragment() {
    private var _fragBinding: CallblacklistCountryblockDialogBinding? = null

    private val fragBinding get() = _fragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = CallblacklistCountryblockDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val adapter = ArrayAdapter(requireContext(),R.layout.list_countries,resources.getStringArray(R.array.countries))
         fragBinding.countrySelect.setAdapter(adapter)


        return root
    }
}