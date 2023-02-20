package com.ab.anti_spam.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalBlockModel(
    var number: String = "",
    var risk: String = "",
    var country: String = "",
    var user_comments: String = "") : Parcelable

