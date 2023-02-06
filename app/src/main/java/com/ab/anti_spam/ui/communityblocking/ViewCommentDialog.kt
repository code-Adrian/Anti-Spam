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
import com.ab.anti_spam.databinding.FragmentViewCommentDialogBinding
import com.ab.anti_spam.models.CommunityBlockingCommentsModel
import com.ab.anti_spam.models.CommunityBlockingModel

class ViewCommentDialog : DialogFragment() {

    private var _fragBinding: FragmentViewCommentDialogBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var commentsModel: CommunityBlockingCommentsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments

        val model: CommunityBlockingCommentsModel? = bundle?.getParcelable("commentsmodel")
        if (model != null) {
            commentsModel = model
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentViewCommentDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        root.minimumWidth = Resources.getSystem().displayMetrics.widthPixels/ 2 + 300
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        loadData()
        closeListener()

        return root
    }

    fun closeListener(){
        fragBinding.close.setOnClickListener{
             this.dismiss()
        }
    }

    fun loadData(){
        fragBinding.date.text = commentsModel.date_Posted
        fragBinding.userCommentDescription.text = commentsModel.comment_Description
    }
}