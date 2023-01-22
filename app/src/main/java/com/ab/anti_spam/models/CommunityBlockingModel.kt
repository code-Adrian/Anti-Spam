package com.ab.anti_spam.models

import android.os.Parcelable
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
}