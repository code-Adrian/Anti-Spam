package com.ab.anti_spam.ui.communityblocking

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ab.anti_spam.R
import com.ab.anti_spam.adapters.ReportChooseNumberAdapter
import com.ab.anti_spam.databinding.FragmentAddBlacklistQuestionDialogBinding
import com.ab.anti_spam.databinding.FragmentAddUserReportChooseNumberDialogBinding
import com.ab.anti_spam.databinding.FragmentAddUserReportDialogBinding
import com.ab.anti_spam.firebase.genUID
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.CallBlacklistModel
import com.ab.anti_spam.models.ChooseNumberModel
import com.ab.anti_spam.models.CommunityBlockingCommentsModel
import com.ab.anti_spam.models.CommunityBlockingModel
import com.ab.anti_spam.ui.auth.LoggedInViewModel
import com.ab.anti_spam.ui.callblacklist.CallblacklistViewModel
import com.ab.anti_spam.ui.callblacklist.OptionsDialog
import com.github.mikephil.charting.utils.ColorTemplate

class AddBlacklistQuestionDialog : DialogFragment() {


    private var _fragBinding: FragmentAddBlacklistQuestionDialogBinding? = null
    private val communityViewModel: CommunityblockingViewModel by activityViewModels()
    private val blacklistViewModel: CallblacklistViewModel by activityViewModels()
    private val fragBinding get() = _fragBinding!!
    private lateinit var model: CommunityBlockingModel
    lateinit var app: Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
        val bundle = arguments

        var model: CommunityBlockingModel? = bundle?.getParcelable("model")
        if (model != null) {
            this.model = model
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentAddBlacklistQuestionDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        fragBinding.dontAdd.setBackgroundColor(ColorTemplate.COLORFUL_COLORS[0])
        fragBinding.add.setBackgroundColor(ColorTemplate.COLORFUL_COLORS[3])

        listeners()

        return root
    }


    private fun listeners(){
        fragBinding.add.setOnClickListener{
            val model = CallBlacklistModel()
            model.by_number = this.model.reported_phone_number.replace("+","")
            blacklistViewModel.addBlacklist(model, app)
            addReport()

        }
        fragBinding.dontAdd.setOnClickListener{
            addReport()
        }
    }

    private fun addReport(){
        communityViewModel.createReport(model,model.user_Id)
        communityViewModel.getRecent100UserReports()
        communityViewModel.getPersonalReports(model.user_Id)
        this.dismiss()
    }

}