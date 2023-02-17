package com.ab.anti_spam.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsModel(
    var uid: String = "",
    var scan_sms: Boolean = true,
    var database_block: Boolean = true,
    var personal_block: Boolean = true,
    var local_store_block: Boolean = true,
    var unknown_block: Boolean = true,
    var community_block_num: Int = 3,
    var theme: Boolean = true,
) : Parcelable
