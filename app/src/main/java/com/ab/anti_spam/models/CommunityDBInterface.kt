package com.ab.anti_spam.models

import androidx.lifecycle.MutableLiveData

interface CommunityDBInterface {
    fun createCommunityReport(model: CommunityBlockingModel, currentUserUID: String)
    fun deleteCommunityReport(model: CommunityBlockingModel,currentUserUID: String)
    fun updateCommunityReport(model: CommunityBlockingModel,currentUserUID: String)
    fun getTop100CommunityReports(model: MutableLiveData<MutableList<CommunityBlockingModel>>)
    fun getCommunityReportById(model: MutableLiveData<CommunityBlockingModel?>, reportId: String)
    fun getCommunityReportByNumber(phoneNumber: String,callback: (CommunityBlockingModel?) -> Unit )
    fun updateCommunityReportComments(model: CommunityBlockingCommentsModel, reportId: String,currentUserUID: String,reportUID: String,updateModel: MutableLiveData<CommunityBlockingModel?>)
    fun deleteComment(model: CommunityBlockingCommentsModel,reportUID: String,reportId: String)
    fun getPersonalReports(model: MutableLiveData<MutableList<CommunityBlockingModel>>,currentUserUID: String)
}