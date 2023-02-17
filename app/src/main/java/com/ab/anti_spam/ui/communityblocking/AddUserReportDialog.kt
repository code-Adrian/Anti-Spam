package com.ab.anti_spam.ui.communityblocking

import android.annotation.SuppressLint
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
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ab.anti_spam.R
import com.ab.anti_spam.adapters.ReportChooseNumberAdapter
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
import java.text.SimpleDateFormat
import java.util.*

class AddUserReportDialog : DialogFragment() {


    private var _fragBinding: FragmentAddUserReportDialogBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var chooseNumberModel: ChooseNumberModel
    private val communityViewModel: CommunityblockingViewModel by activityViewModels()
    private lateinit var uid: String
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    lateinit var app: Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
        val bundle = arguments
        var model: ChooseNumberModel? = bundle?.getParcelable("model")
        if (model != null) {
            chooseNumberModel = model
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadFireUser()
        _fragBinding = FragmentAddUserReportDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        root.minimumWidth = Resources.getSystem().displayMetrics.widthPixels/ 2 + 300


        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        fragBinding.header.text = chooseNumberModel.number

        populateDropDownMenus()
        hideKeyboardListeners()
        automaticCountryDetection()
        navigationListener()
        addReport()
        darkTheme()
        return root
    }

    private fun loadFireUser(){
        loggedInViewModel.liveFirebaseUser.observe(viewLifecycleOwner,{
            uid=it.uid
            communityViewModel.UID.value = uid
        })
    }

    @SuppressLint("SimpleDateFormat")
    fun addReport(){
        fragBinding.AddReport.setOnClickListener{
            val country = fragBinding.countrySelect.text.toString()
            val warning = fragBinding.warningSelect.text.toString()
            val description = fragBinding.titleDescription.text.toString()
            val numberReported = chooseNumberModel.number
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

                val emptyCommentsModel = mutableListOf<CommunityBlockingCommentsModel>()
                val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                val date = dateFormat.format(Date())

                val model =  CommunityBlockingModel(genUID(),uid, description,numberReported, warning,countryReported,date,emptyCommentsModel)

                dismiss()
                val bundle = Bundle()
                bundle.putParcelable("model",model)
                val addToBlacklistQuestionDialog = AddBlacklistQuestionDialog()
                addToBlacklistQuestionDialog.arguments = bundle
                addToBlacklistQuestionDialog.show(parentFragmentManager,null)


            }
        }
    }



    fun navigationListener(){
        fragBinding.goBack.setOnClickListener{
            dismiss()
            AddUserReportChooseNumberDialog().show(parentFragmentManager,null)
        }
        fragBinding.cancel.setOnClickListener{
            this.dismiss()
        }
    }

    fun automaticCountryDetection(){
        for(i in resources.getStringArray(R.array.countries)){
            val chop = i.split("(")[1].replace(")","").replace("(","")
            if(chooseNumberModel.number.startsWith(chop)){
             fragBinding.countrySelect.setText(i)
            }
        }
        populateDropDownMenus()
    }

    fun populateDropDownMenus(){
        val countryAdapter = ArrayAdapter(requireContext(),R.layout.list_countries,resources.getStringArray(R.array.countries))
        val warningAdapter = ArrayAdapter(requireContext(),R.layout.list_countries,resources.getStringArray(R.array.warning))
        fragBinding.countrySelect.setAdapter(countryAdapter)
        fragBinding.warningSelect.setAdapter(warningAdapter)
    }

    fun hideKeyboardListeners(){
        fragBinding.countrySelect.setOnClickListener{
            if(it.hasFocus()) {
                val inputMethodManager =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
            }
        }
        fragBinding.warningSelect.setOnClickListener{
            if(it.hasFocus()) {
                val inputMethodManager =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
            }
        }
    }

    fun darkTheme(){
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            fragBinding.root.setBackgroundResource(R.drawable.dialog_background_dark)
            fragBinding.header.setTextColor(Color.WHITE)
        }
    }
}