package com.example.kodetraineetest.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

    fun makeCall(context: Context, mob: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL)

        intent.data = Uri.parse("tel:$mob")
        context.startActivity(intent)
    } catch (e: java.lang.Exception) {
        Toast.makeText(context,
            "Unable to call at this time", Toast.LENGTH_SHORT).show()
    }
}