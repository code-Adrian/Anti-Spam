package com.ab.anti_spam.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SMSBlacklistModel(
    var id: Long = 0,
    var by_keyword: String = "",
    var by_regex: String = "") : Parcelable
{
    @Exclude
    fun map(): Map<String, Any?> {
        return mapOf<String,Any?>(
            "id" to id,
            "by_keyword" to by_keyword,
            "by_regex" to by_regex
        )
    }
}