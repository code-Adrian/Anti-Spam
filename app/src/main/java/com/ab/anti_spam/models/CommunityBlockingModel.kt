package com.ab.anti_spam.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommunityBlockingModel(    var id: Long = 0,
                                      var user_Id: String = "",
                                      var report_title: String = "",
                                      var report_Description: String = "",
                                      var reported_phone_number: String = "",
                                      var risk_Level: String = "",
                                      var country: String = "",
                                      var user_comments: MutableList<CommunityBlockingCommentsModel>) : Parcelable {
    @Exclude
    fun map(): Map<String, Any?> {
        return mapOf<String,Any?>(
            "id" to id,
            "user_id" to user_Id,
            "report_title" to report_title,
            "report_Description" to report_Description,
            "reported_phone_number" to reported_phone_number,
            "risk_level" to risk_Level,
            "country" to country,
            "user_comments" to user_comments,
        )
    }
}