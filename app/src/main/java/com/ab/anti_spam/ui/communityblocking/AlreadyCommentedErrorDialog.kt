package com.ab.anti_spam.ui.communityblocking

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.FragmentAddCommentErrorDialogBinding
import com.ab.anti_spam.databinding.FragmentAlreadyCommentedErrorDialogBinding

class AlreadyCommentedErrorDialog : DialogFragment() {

    private var _fragBinding: FragmentAlreadyCommentedErrorDialogBinding? = null
    private val fragBinding get() = _fragBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentAlreadyCommentedErrorDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        root.minimumWidth = Resources.getSystem().displayMetrics.widthPixels/ 2 + 300
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        acknowledgeMessagelistener()

        return root
    }


    fun acknowledgeMessagelistener(){
        fragBinding.acknowledge.setOnClickListener{
            this.dismiss()
        }
    }

}