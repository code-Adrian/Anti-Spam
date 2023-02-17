package com.ab.anti_spam.ui.communityblocking

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.FragmentAddUserReportDialogBinding
import com.ab.anti_spam.databinding.FragmentEditUserReportDialogBinding
import com.ab.anti_spam.firebase.genUID
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.ChooseNumberModel
import com.ab.anti_spam.models.CommunityBlockingCommentsModel
import com.ab.anti_spam.models.CommunityBlockingModel
import com.ab.anti_spam.ui.auth.LoggedInViewModel

class EditUserReportDialog : DialogFragment() {

    private var _fragBinding: FragmentEditUserReportDialogBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val communityViewModel: CommunityblockingViewModel by activityViewModels()
    private lateinit var model: CommunityBlockingModel
    lateinit var app: Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
        val bundle = arguments
        val model: CommunityBlockingModel? = bundle?.getParcelable("model")
        if (model != null) {
            this.model = model
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragBinding = FragmentEditUserReportDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        root.minimumWidth = Resources.getSystem().displayMetrics.widthPixels/ 2 + 300
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        populateDropDownMenus()
        exitListener()
        editReport()
        darkTheme()
        return root
    }


    fun editReport(){
        //Listener
        fragBinding.EditReport.setOnClickListener{
            val country = fragBinding.countrySelect.text.toString()
            val warning = fragBinding.warningSelect.text.toString()
            val description = fragBinding.editDescription.text.toString()
            val countryReported = country.split("(")[0]

            val unfocusedColor = Color.RED
            val states = arrayOf(intArrayOf(android.R.attr.state_focused))
            val colors = intArrayOf(unfocusedColor)
            val colorStateList = ColorStateList(states, colors)


            if(country.isEmpty()){
                fragBinding.textInputLayoutCountry.setBoxStrokeColorStateList(colorStateList)
            }
            if(warning.isEmpty()){
                fragBinding.textInputLayoutWarning.setBoxStrokeColorStateList(colorStateList)
            }
            if(description.isEmpty() || description.length < 5){
                fragBinding.textInputLayoutDescription.setBoxStrokeColorStateList(colorStateList)
            }

            if(country.isNotEmpty() && warning.isNotEmpty() && description.isNotEmpty() && description.length > 5){
                val updatedModel = model
                updatedModel.country = countryReported
                updatedModel.risk_Level = warning
                updatedModel.report_Description = description

                communityViewModel.updateReport(model,communityViewModel.UID.value!!)

                val bundle = Bundle()
                bundle.putParcelable("model",updatedModel)

                setFragmentResult("model",bundle)

                this.dismiss()
            }
        }
    }

    fun populateDropDownMenus(){

        fragBinding.countrySelect.setText(model.country)
        fragBinding.warningSelect.setText(model.risk_Level)
        fragBinding.editDescription.setText(model.report_Description)

        val countryAdapter = ArrayAdapter(requireContext(),R.layout.list_countries,resources.getStringArray(R.array.countries))
        val warningAdapter = ArrayAdapter(requireContext(),R.layout.list_countries,resources.getStringArray(R.array.warning))
        fragBinding.countrySelect.setAdapter(countryAdapter)
        fragBinding.warningSelect.setAdapter(warningAdapter)

    }

    fun exitListener(){
        fragBinding.cancel.setOnClickListener{
            this.dismiss()
        }
    }

    fun darkTheme(){
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            fragBinding.root.setBackgroundResource(R.drawable.dialog_background_dark)
            fragBinding.header.setTextColor(Color.WHITE)
        }else{
            fragBinding.root.setBackgroundResource(R.drawable.dialog_background)
        }
    }
}