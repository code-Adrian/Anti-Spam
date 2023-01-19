package com.ab.anti_spam.ui.smsblacklist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.SmsblacklistOptionsDialogBinding

class SMSOptionsDialog: DialogFragment() {

    private var _fragBinding: SmsblacklistOptionsDialogBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = SmsblacklistOptionsDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        listeners()



        return root
    }

    private fun listeners(){
        fragBinding.keywordBlock.setOnClickListener{
            dismiss()
            findNavController().navigate(R.id.action_nav_sms_blacklist_to_keywordBlockDialog)
        }
        fragBinding.regexBlock.setOnClickListener{
            dismiss()
            findNavController().navigate(R.id.action_nav_sms_blacklist_to_SMSRegexBlockDialog)
        }
    }

}