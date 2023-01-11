package com.ab.anti_spam.ui.callblacklist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ab.anti_spam.databinding.CallblacklistRegexblockDialogBinding

class RegexBlockDialog: DialogFragment() {

    private var _fragBinding: CallblacklistRegexblockDialogBinding? = null

    private val fragBinding get() = _fragBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = CallblacklistRegexblockDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        return root
    }
}