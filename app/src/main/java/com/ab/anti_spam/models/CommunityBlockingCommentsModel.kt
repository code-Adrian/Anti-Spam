package com.ab.anti_spam.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommunityBlockingCommentsModel(
    var id: Long = 0,
    var user_Id_comment: String = "",
    var comment_Description: String = "",
    var risk_Level: String = "",
    var date_Posted: String = "") : Parcelable {
    @Exclude
    fun map(): Map<String, Any?> {
        return mapOf<String,Any?>(
            "id" to id,
            "user_Id_comment" to user_Id_comment,
            "comment_Description" to comment_Description,
            "risk_Level" to risk_Level,
            "date_Posted" to date_Posted
        )
    }
}