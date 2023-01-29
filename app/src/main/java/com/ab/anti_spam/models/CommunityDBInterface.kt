package com.ab.anti_spam.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface CommunityDBInterface {
    fun createCommunityReport(model: CommunityBlockingModel, currentUserUID: String)
    fun deleteCommunityReport(model: CommunityBlockingModel,currentUserUID: String)
    fun updateCommunityReport(model: CommunityBlockingModel,currentUserUID: String)
    fun getTop100CommunityReports(model: MutableLiveData<MutableList<CommunityBlockingModel>>)
    fun getPersonalReports(model: MutableLiveData<MutableList<CommunityBlockingModel>>,currentUserUID: String)
}