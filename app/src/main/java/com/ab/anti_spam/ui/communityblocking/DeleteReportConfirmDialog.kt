package com.ab.anti_spam.ui.communityblocking

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.FragmentDeleteReportConfirmDialogBinding
import com.ab.anti_spam.databinding.FragmentEditUserReportDialogBinding
import com.ab.anti_spam.models.CommunityBlockingModel
import com.github.mikephil.charting.utils.ColorTemplate

class DeleteReportConfirmDialog : DialogFragment() {

    private var _fragBinding: FragmentDeleteReportConfirmDialogBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val communityViewModel: CommunityblockingViewModel by activityViewModels()
    private lateinit var model: CommunityBlockingModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        _fragBinding = FragmentDeleteReportConfirmDialogBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        root.minimumWidth = Resources.getSystem().displayMetrics.widthPixels/ 2 + 300
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        fragBinding.delete.setBackgroundColor(ColorTemplate.COLORFUL_COLORS[0])
        listeners()
        darkTheme()
        return root
    }



    fun listeners(){
        fragBinding.delete.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("delete","delete")

            setFragmentResult("delete",bundle)

            this.dismiss()
        }
        fragBinding.dontDelete.setOnClickListener{
            this.dismiss()
        }

    }

    fun darkTheme(){
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            fragBinding.root.setBackgroundResource(R.drawable.dialog_background_dark)
        }else{
            fragBinding.root.setBackgroundResource(R.drawable.dialog_background)
        }
    }
}