package com.ab.anti_spam.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
class ChooseNumberModel (var number: String = "",var date: String = "", var cached_name: String = "") : Parcelable

