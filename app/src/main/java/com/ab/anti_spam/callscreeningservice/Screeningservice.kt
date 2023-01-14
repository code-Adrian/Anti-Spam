package com.ab.anti_spam.callscreeningservice

import android.telecom.Call
import android.telecom.CallScreeningService
import android.telecom.CallScreeningService.CallResponse.Builder
import com.ab.anti_spam.localstorage.CallBlacklistStorage

class Screeningservice: CallScreeningService() {

    override fun onScreenCall(callDetails: Call.Details) {
        val phoneNumber = getPhoneNumber(callDetails)
        var response = CallResponse.Builder()
        response = handlePhoneCall(response, phoneNumber)

        respondToCall(callDetails, response.build())
    }

    private fun handlePhoneCall(
        response: CallResponse.Builder,
        phoneNumber: String
    ): CallResponse.Builder {
        println(String.format("Incoming call from %s", phoneNumber))

        val localStorage = CallBlacklistStorage(this.applicationContext)
        val array = localStorage.getAll()
        val countryBlockArray = arrayListOf<String>()
        val numberBlockArray = arrayListOf<String>()
        val regexBlockArray = arrayListOf<String>()

        for(i in array){
            if(i.by_country.isNotEmpty()) {
                countryBlockArray.add(i.by_country)
            }
            if(i.by_number.isNotEmpty()) {
                numberBlockArray.add(i.by_number)
            }
            if(i.by_regex.isNotEmpty()) {
                regexBlockArray.add(i.by_regex)
            }
        }

        if(countryBlockArray.size > 0){
            val cleanArray = cleanCountry(countryBlockArray)

            for (i in cleanArray){
                if(i in phoneNumber){
                    blockNumber(response,phoneNumber)
                }
            }
        }
        if(numberBlockArray.size > 0){
            val cleanArray = cleanNumber(numberBlockArray)
            for(i in cleanArray){
                if(i in phoneNumber){
                    blockNumber(response,phoneNumber)
                }
            }
        }
        if(regexBlockArray.size > 0){
            val cleanArray = cleanRegex(regexBlockArray)
            for(i in cleanArray){
                if(phoneNumber.matches(i.toRegex())){
                    blockNumber(response,phoneNumber)
                }
            }
        }

        return response
    }

    private fun getPhoneNumber(callDetails: Call.Details): String {
        return callDetails.handle.toString().replace("tel:","").replace("%2B","+")
        //.removeTelPrefix().parseCountryCode()
    }

    private fun cleanCountry(countryArray: ArrayList<String>): java.util.ArrayList<String>{
        val cleanArray = arrayListOf<String>()

        for (i in countryArray){
            cleanArray.add(i.split("(")[1].replace(")","").replace("(","").trim())
        }

        return cleanArray
    }

    private fun cleanNumber(numberArray: ArrayList<String>): java.util.ArrayList<String>{
        val cleanArray = arrayListOf<String>()

        for (i in numberArray){

            //If the first number is 0, drop the 0
            if(i.get(0).toString().equals("0")){
                cleanArray.add(i.drop(1).trim())
            }else{
                cleanArray.add(i.trim())
            }
        }
        return cleanArray
    }

    private fun cleanRegex(regexArray: ArrayList<String>): java.util.ArrayList<String>{
        val cleanArray = arrayListOf<String>()

        for(i in regexArray){
            cleanArray.add(i.trim())
        }
        return cleanArray
    }

    private fun blockNumber(response: Builder,phoneNumber: String){
        response.apply {
            println((String.format("Rejected call from %s", phoneNumber)))
            setRejectCall(true)
            setDisallowCall(true)
            setSkipCallLog(true)
        }
    }

}