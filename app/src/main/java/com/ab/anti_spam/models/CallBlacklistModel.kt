package com.ab.anti_spam.models
import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CallBlacklistModel(var user_id: String = "",
                              var by_country: MutableList<String>,
                              var by_number: MutableList<String>,
                              var by_regex: MutableList<String>) : Parcelable
{
    @Exclude
    fun map(): Map<String, Any?> {
        return mapOf<String,Any?>(
            "user_id" to user_id,
            "by_country" to by_country,
            "by_number" to by_number,
            "by_regex" to by_regex
        )
    }
}