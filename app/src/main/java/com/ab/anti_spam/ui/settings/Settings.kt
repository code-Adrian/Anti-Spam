package com.ab.anti_spam.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ab.anti_spam.R
import com.ab.anti_spam.databinding.FragmentCommunityblockingBinding
import com.ab.anti_spam.databinding.FragmentSettingsBinding
import com.ab.anti_spam.main.Main

class Settings : Fragment() {

    lateinit var app: Main
    private var _fragBinding: FragmentSettingsBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as Main
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        settingListeners()

        return root
    }

    fun settingListeners(){
        fragBinding.toggleSmsScan.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked){

            }else{

            }
        }
        fragBinding.toggleDbBlocking.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked){

            }else{

            }
        }
        fragBinding.toggleLocalBlocking.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked){

            }else{

            }
        }
        fragBinding.togglePersonalBlocking.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked){

            }else{

            }
        }
        fragBinding.toggleTheme.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked){

            }else{

            }
        }
        fragBinding.toggleUnknownBlocking.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked){

            }else{

            }
        }

        fragBinding.commentSlider.addOnChangeListener{slider,value,fromUser ->
            fragBinding.commentNum.setText(value.toString().replace(".0",""))
        }

        fragBinding.downlaodButton.setOnClickListener{

        }

    }

}