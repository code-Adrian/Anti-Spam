package com.ab.anti_spam.ui.smsblacklist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ab.anti_spam.databinding.CallblacklistNumberblockDialogBinding
import com.ab.anti_spam.databinding.SmsblacklistKeywordblockDialogBinding
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.CallBlacklistModel
import com.ab.anti_spam.models.SMSBlacklistModel
import com.ab.anti_spam.ui.callblacklist.CallblacklistViewModel

class KeywordBlockDialog: DialogFragment() {

    private var _fragBinding: SmsblacklistKeywordblockDialogBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val blacklistViewModel: SmsblacklistViewModel by activityViewModels()
    lateinit var app: Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = SmsblacklistKeywordblockDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        fragBinding.WarnByKeyword.setOnClickListener{
            addWarning()
        }

        return root
    }

    private fun addWarning(){
        if(!fragBinding.textInputLayout.editText?.text.toString().isEmpty()) {
            val data = fragBinding.textInputLayout.editText?.text.toString()
            val model = SMSBlacklistModel()
            model.by_keyword = data
            blacklistViewModel.addBlacklist(model, app)
            dismiss()
            fragBinding.WarnByKeyword.isEnabled = false
        }
    }

}