package com.ab.anti_spam.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.ab.anti_spam.R

class Settings : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}