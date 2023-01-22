package com.ab.anti_spam.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommunityBlockingCommentsModel(
    var id: Long = 0,
    var comment_Description: String = "",
    var risk_Level: String = "",
    var date_Posted: String = "") : Parcelable {
}