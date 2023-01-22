package com.ab.anti_spam.smswarning

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.PixelFormat
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ab.anti_spam.R

@SuppressLint("CutPasteId", "SetTextI18n")
fun display(context: Context, msg_from: String){
    val width = Resources.getSystem().displayMetrics.widthPixels - 250
    val height = Resources.getSystem().displayMetrics.heightPixels /2


    val Params: WindowManager.LayoutParams = WindowManager.LayoutParams(width, height,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
        PixelFormat.TRANSLUCENT)



    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = layoutInflater.inflate(R.layout.warning_overlay,null)

    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.addView(layout, Params)

    val button: Button = layout.findViewById(R.id.close_overlay)
    button.width = width-175

    val warningText: TextView = layout.findViewById(R.id.warning_message);
    warningText.setText("SMS from (${msg_from}) has been detected as FRADUELENT.\nPlease take care.")

        button.setOnClickListener {
            wm.removeView(layout)
            try {
                val overlayintent = Intent(context, overlayservice::class.java)
                context.stopService(overlayintent)
            }catch(e:Exception){
                println(e)
            }
            }


}