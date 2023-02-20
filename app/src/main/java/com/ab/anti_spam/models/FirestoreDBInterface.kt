package com.ab.anti_spam.models

interface FirestoreDBInterface {

    fun checkNumber(phoneNumber: String,callback: (Boolean) -> Unit )
    fun getBlockList(callback: (blocklist : MutableList<LocalBlockModel>) -> Unit )
}