package com.ab.anti_spam.ui.settings

import androidx.lifecycle.ViewModel
import com.ab.anti_spam.databinding.FragmentSettingsBinding
import com.ab.anti_spam.localstorage.SettingsStorage
import com.ab.anti_spam.main.Main
import com.ab.anti_spam.models.SettingsModel

class SettingsViewModel: ViewModel() {

    fun updateGUISettings(binding: FragmentSettingsBinding, app: Main,uid: String){
        //Getting all settings in storage from all users.
        val settings = SettingsStorage(app.applicationContext)
        val allSettings = settings.getAll()
        //Finding user settings based on UID & updating GUI elements
        for (i in allSettings){
            if(i.uid.equals(uid)){
                binding.toggleSmsScan.isChecked = i.scan_sms
                binding.toggleDbBlocking.isChecked = i.database_block
                binding.toggleLocalBlocking.isChecked = i.local_store_block
                binding.togglePersonalBlocking.isChecked = i.personal_block
                binding.toggleTheme.isChecked = i.theme
                binding.toggleUnknownBlocking.isChecked = i.unknown_block
                binding.commentSlider.value = i.community_block_num.toFloat()

            }
        }
    }

    fun updateSettings(model: SettingsModel,app: Main){
        val settings = SettingsStorage(app.applicationContext)
        settings.update(model)
    }
}