package com.ab.anti_spam.smswarning

import android.app.Service
import android.content.Intent
import android.os.IBinder

class overlayservice: Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            val from = intent?.getStringExtra("msg_from").toString()
            display(this,from)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}