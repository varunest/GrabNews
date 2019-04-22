package com.varunest.grabnews.utils

import android.content.Context
import android.net.ConnectivityManager
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import java.io.InputStream
import java.io.OutputStream


object CommonUtils {
    private const val SECOND_MILLIS = 1000L
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    private const val MONTH_MILLIS = 30 * DAY_MILLIS
    private const val YEAR_MILLIS = 12 * MONTH_MILLIS

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun getTimeAgo(date: Date): String {
        var time = date.time
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return "in the future"
        }

        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> "moments ago"
            diff < 2 * MINUTE_MILLIS -> "a minute ago"
            diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} minutes ago"
            diff < 2 * HOUR_MILLIS -> "an hour ago"
            diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} hours ago"
            diff < 48 * HOUR_MILLIS -> "yesterday"
            diff <  MONTH_MILLIS -> "${diff / DAY_MILLIS} days ago"
            diff < 2 * MONTH_MILLIS -> "1 month ago"
            diff < 12 * MONTH_MILLIS -> "${diff / MONTH_MILLIS} months ago"
            diff < 2 * YEAR_MILLIS -> "1 year ago"
            else -> "${diff / YEAR_MILLIS} years ago"
        }
    }

    @JvmStatic
    fun copyStream(inputStream: InputStream, outputStream: OutputStream) {
        val bufferSize = 1024
        try {
            val bytes = ByteArray(bufferSize)
            while (true) {
                val count = inputStream.read(bytes, 0, bufferSize)
                if (count == -1)
                    break
                outputStream.write(bytes, 0, count)
            }
        } catch (ex: Exception) {
        }

    }

}