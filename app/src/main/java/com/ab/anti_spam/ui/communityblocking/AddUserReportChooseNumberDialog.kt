package com.ab.anti_spam.ui.communityblocking

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.CallLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ab.anti_spam.R
import com.ab.anti_spam.adapters.ReportChooseNumberAdapter
import com.ab.anti_spam.adapters.chooseNumberListener
import com.ab.anti_spam.databinding.FragmentAddUserReportChooseNumberDialogBinding
import com.ab.anti_spam.models.ChooseNumberModel
import com.ab.anti_spam.models.CommunityBlockingModel
import java.text.SimpleDateFormat
import java.util.*

class AddUserReportChooseNumberDialog : DialogFragment(),chooseNumberListener {

    private var _fragBinding: FragmentAddUserReportChooseNumberDialogBinding? = null
    private val fragBinding get() = _fragBinding!!
    lateinit var adapter: ReportChooseNumberAdapter
    private val communityViewModel: CommunityblockingViewModel by activityViewModels()
    private var globalModelSelect: ChooseNumberModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentAddUserReportChooseNumberDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        root.minimumWidth = Resources.getSystem().displayMetrics.widthPixels/ 2 + 300
        //Resetin
        communityViewModel.observableCommunityReportExists.value = null
        observer()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        renderRecyclerView(getRecentCalls())
        darkTheme()
        return root
    }




    private fun renderRecyclerView(model: ArrayList<ChooseNumberModel>){
        fragBinding.choosenumberRecyclerview.adapter?.notifyDataSetChanged()
        fragBinding.choosenumberRecyclerview.layoutManager = LinearLayoutManager(activity)
        fragBinding.choosenumberRecyclerview.adapter = ReportChooseNumberAdapter(model,this)
        adapter = fragBinding.choosenumberRecyclerview.adapter as ReportChooseNumberAdapter
    }

    @SuppressLint("Range")
    fun getRecentCalls() : ArrayList<ChooseNumberModel>{
        val recentCalls  = ArrayList<ChooseNumberModel>()

        val callLogUri = CallLog.Calls.CONTENT_URI
        val variables = arrayOf(CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME, CallLog.Calls.DATE)

        val cursor = context?.contentResolver?.query(callLogUri,variables,null,null,CallLog.Calls.DEFAULT_SORT_ORDER)

        if(cursor != null && cursor.count > 0){
            while (cursor.moveToNext()){
                val number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))
                var cached_name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME))
                val date = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE))

                val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val dateString = dateFormatter.format(Date(date))

                if(cached_name == null || cached_name.equals("null")){
                    cached_name = "Contact not saved"
                }

                val model = ChooseNumberModel(number,dateString, cached_name)
                if(!recentCalls.any { it.number == number }) {
                    recentCalls.add(model)
                }
            }
            cursor.close()
        }
        return recentCalls
    }

    override fun onChoose(model: ChooseNumberModel) {
        //Updates the global variable to the chosen model.
        this.globalModelSelect = model
        //Triggers firebase to get the report and get a callback which updates the observer() function.
        communityViewModel.getUserReportByNumber(model.number)
    }

    fun observer(){
        communityViewModel.observableCommunityReportExists.observe(viewLifecycleOwner,{
            if(it != null){
                //Display dialog that tells the user the report already exists and if the user wants to view the existing report
                alreadyExists(it)

            }else{
                globalModelSelect?.let { next(it) }
            }
        })
    }

    fun alreadyExists(model: CommunityBlockingModel){
        this.dismiss()
        val bundle = Bundle()
        bundle.putParcelable("model",model)
        val reportExistsVisitDialog = ReportExistsVisitDialog()
        reportExistsVisitDialog.arguments = bundle
        reportExistsVisitDialog.show(parentFragmentManager,null)

    }

    fun next(model: ChooseNumberModel){
        this.dismiss()
        val bundle = Bundle()
        bundle.putParcelable("model",model)
        findNavController().navigate(R.id.action_nav_community_blocking_to_addUserReportDialog2,bundle)
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