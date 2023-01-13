package com.ab.anti_spam.callscreeningservice

import android.telecom.Call
import android.telecom.CallScreeningService

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

        val FORBIDDEN_PHONE_CALL_NUMBER = "<<PHONE NUMBER>>"
        if (FORBIDDEN_PHONE_CALL_NUMBER in phoneNumber) {
            response.apply {
                setRejectCall(true)
                setDisallowCall(true)
                setSkipCallLog(true)
                //
                println((String.format("Rejected call from %s", phoneNumber)))
            }
        } else {
            println(String.format("Incoming call from %s", phoneNumber))
        }
        return response
    }

    private fun getPhoneNumber(callDetails: Call.Details): String {
        return callDetails.handle.toString().replace("tel:","").replace("%2B","+")
        //.removeTelPrefix().parseCountryCode()
    }
}