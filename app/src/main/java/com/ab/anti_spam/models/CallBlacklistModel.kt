package com.ab.anti_spam.models
import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CallBlacklistModel(
    var id: Long = 0,
    var by_country: String = "",
    var by_number: String = "",
    var by_regex: String = "") : Parcelable
{
    @Exclude
    fun map(): Map<String, Any?> {
        return mapOf<String,Any?>(
            "id" to id,
            "by_country" to by_country,
            "by_number" to by_number,
            "by_regex" to by_regex
        )
    }
}