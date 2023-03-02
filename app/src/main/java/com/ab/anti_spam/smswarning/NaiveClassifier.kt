
package com.ab.anti_spam.smswarning

import android.content.Context
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.io.File
import java.net.URL


public class NaiveClassifier() {

    fun isFraudulent(context: Context,message: String) : Boolean{
        if(!Python.isStarted()){
            Python.start(AndroidPlatform(context))
        }
        val py = Python.getInstance()
        val pyObj : PyObject = py.getModule("naive")

        val pyobj : PyObject = pyObj.callAttr("main",message)
        if(pyobj.toString().contains("fraudulent")){
            return true
        }
        if(pyobj.toString().contains("legitimate")){
            return false
        }

        return false
    }



}














