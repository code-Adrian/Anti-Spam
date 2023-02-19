package com.ab.anti_spam.helpers

import android.content.Context
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
object UIDsave {
     fun saveUidToFile(uid: String, context: Context) {
        try {
            val fos: FileOutputStream = context.openFileOutput("uid.txt", Context.MODE_PRIVATE)
            fos.write(uid.toByteArray())
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


     fun getUidFromFile(context: Context): String? {
        return try {
            val scanner = Scanner(context.openFileInput("uid.txt"))
            val uid: String = scanner.useDelimiter("\\A").next()
            scanner.close()
            uid
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}