package com.ab.anti_spam.ui.communityblocking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ab.anti_spam.firebase.FirebaseDBManager
import com.ab.anti_spam.models.CommunityBlockingModel
import com.google.firebase.auth.FirebaseUser

class CommunityblockingViewModel : ViewModel() {

    //Community Report List
    private val communityReportList = MutableLiveData<MutableList<CommunityBlockingModel>>()
    val observableCommunityReportList : LiveData<MutableList<CommunityBlockingModel>>
    get() = communityReportList

    //Personal Report List
    private val personalReportList = MutableLiveData<MutableList<CommunityBlockingModel>>()
    val observablePersonalReportList : LiveData<MutableList<CommunityBlockingModel>>
    get() = personalReportList

    //UID
    val UID = MutableLiveData<String>()
    val observableUID : LiveData<String>
        get() = UID

   private val status =  MutableLiveData<Boolean>()
   val observableStatus: LiveData<Boolean>
   get() = status


fun createReport(model: CommunityBlockingModel,currentUserUID: String){
    status.value = try{
        FirebaseDBManager.createCommunityReport(model,currentUserUID)
        true
    }catch (e: Exception){
        false
    }
}

    fun getRecent100UserReports(){
        status.value = try{
            FirebaseDBManager.getTop100CommunityReports(communityReportList)
            true
        }catch (e:Exception){
            false
        }
    }

    fun deleteReport(model: CommunityBlockingModel,currentUserUID: String){
        status.value = try{
            FirebaseDBManager.deleteCommunityReport(model,currentUserUID)
            true
        }catch (e:Exception){
            false
        }
    }

    fun updateReport(model: CommunityBlockingModel,currentUserUID: String){
        status.value = try{
            FirebaseDBManager.updateCommunityReport(model,currentUserUID)
            true
        }catch (e:Exception){
            false
        }
    }

    fun getPersonalReports(currentUserUID : String){
        status.value = try{
            FirebaseDBManager.getPersonalReports(personalReportList,currentUserUID)
            true
        }catch (e:Exception){
            false
        }
    }

}